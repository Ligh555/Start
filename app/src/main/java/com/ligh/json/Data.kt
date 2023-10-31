import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlin.math.max


data class BjsBean(
    val graphBean: GraphBean,
    val ipoReportData: Map<Int, List<String>>,
    val ipoReadyData: Map<Int, List<String>>,
    val convertReportData: Map<Int, List<String>>,
    val convertReadyData: Map<Int, List<String>>
)

data class GraphBean(
    val volumData: List<Double>,
    val volumFlag: List<Int>,
    val volumDataMax: Double,
    val shareData: List<Double>,
    val shareDataMax: Double,
    val axisTime: List<String>,
)

@Serializable
data class Data(
    val date: DateData,
    @SerialName("fast_news")
    val fastNews: List<String>,
    @SerialName("market_overview")
    val marketOverview: MarketOverview,
    @SerialName("range_view")
    val rangeView: RangeView,
    val ipo: IpoData,
    val convert: ConvertData,
    val funds: List<FundData>
)

@Serializable
data class DateData(
    val time: String = "",
    val week: Int = 0,
    @SerialName("last_trade_day")
    val lastTradeDay: String = ""
)

@Serializable
data class MarketOverview(
    val up: Int = 0,
    val plate: Int = 0,
    val down: Int = 0,
    @SerialName("cur_trade")
    val curTrade: Double = 0.0,
    @SerialName("last_trade")
    val lastTrade: Double = 0.0
)

@Serializable
data class RangeView(
    val share: List<ShareData> = emptyList(),
    val volum: List<VolumeData> = emptyList()
)

@Serializable
data class VolumeData(
    val date: String = "",
    val flag: Int = 1,
    @SerialName("total_vol")
    val totalValue: Double = 0.0
)

@Serializable
data class ShareData(
    val date: String = "",
    @SerialName("total_value")
    val totalValue: Double = 0.0
)

@Serializable
data class IpoData(
    @SerialName("ipo_report")
    val ipoReport: List<ListData> = emptyList(),
    @SerialName("ipo_ready")
    val ipoReady: List<ListData> = emptyList()
)

@Serializable
data class ListData(
    val market: String = "--",
    @SerialName("stock_code")
    val stockCode: String = "--",
    @SerialName("industry_pe")
    val industryPe: Double = 0.0,
    @SerialName("industry_belong")
    val industryBelong: String = "--",
    @SerialName("stock_name")
    val stockName: String = "--",
    @SerialName("now_price")
    val nowPrice: Double = 0.0,
    @SerialName("dy_pe")
    val dyPe: Double = 0.0,
    @SerialName("up_rate")
    val upRate: Double = 0.0
)

@Serializable
data class ConvertData(
    @SerialName("convert_report")
    val convertReport: List<ListData> = emptyList(),
    @SerialName("convert_ready")
    val convertReady: List<ListData> = emptyList()
)

@Serializable
data class FundData(
    val type: String = "--",
    @SerialName("found_code")
    val foundCode: String = "--",
    @SerialName("found_name")
    val foundName: String = "--",
    @SerialName("now_profit")
    val nowProfit: String = "--"
)

@Serializable
data class JsonResponse(
    @SerialName("status_code")
    val statusCode: Int = 0,
    @SerialName("status_msg")
    val statusMsg: String = "",
    val data: Data? = null,
    @SerialName("trace_id")
    val traceId: String = ""
)

fun transBean(jsonData: JsonResponse): BjsBean? {
    jsonData.data?.let {
        val volumRangeData = it.rangeView.volum
        val shareRangeData = it.rangeView.share
        // 初始化两个列表，并确保它们的大小都为60，如果不足60，可以添加默认值
        val volumTotalValues = mutableListOf<Double>()
        val shareTotalValues = mutableListOf<Double>()
        var volumMax = 0.0
        var shareMax = 0.0
        val flags = mutableListOf<Int>()
        val targetSize = 60
        val axisTime = when {
            volumRangeData.isEmpty() -> listOf("", "", "")
            volumRangeData.size >= 30 -> listOf(
                volumRangeData[0].date,
                volumRangeData[30].date,
                volumRangeData.last().date
            )

            else -> listOf(volumRangeData[0].date, "", "")
        }

        for (i in 0 until targetSize) {
            if (i < volumRangeData.size) {
                volumTotalValues.add(volumRangeData[i].totalValue)
                volumMax = max(volumMax, volumRangeData[i].totalValue)
                shareTotalValues.add(shareRangeData[i].totalValue)
                shareMax = max(shareMax, shareRangeData[i].totalValue)
                flags.add(volumRangeData[i].flag)
            } else {
                volumTotalValues.add(0.0) // 默认值
                shareTotalValues.add(0.0) // 默认值
                flags.add(1) // 默认值
            }
        }
        return BjsBean(
            GraphBean(
                volumTotalValues,
                flags,
                volumMax,
                shareTotalValues,
                shareMax,
                axisTime
            ),
            processData(it.ipo.ipoReady),
            processData(it.ipo.ipoReport),
            processData(it.convert.convertReady),
            processData(it.convert.convertReport),
        )
    }
    return null
}

fun processData(data: List<ListData>): Map<Int, List<String>> {
    val keys = listOf(0, 1, 2, 3, 4, 5, 6, 7)
    val dataMap = mutableMapOf<Int, MutableList<String>>()
    for (item in data) {
        val propertiesList = listOf(
            item.market,
            item.stockCode,
            item.industryPe.toString(),
            item.industryBelong,
            item.stockName,
            item.nowPrice.toString(),
            item.dyPe.toString(),
            item.upRate.toString()
        )
        propertiesList.forEachIndexed { index, value ->
            dataMap.getOrPut(keys[index]) { mutableListOf() }.add(value)
        }
    }
    return dataMap
}