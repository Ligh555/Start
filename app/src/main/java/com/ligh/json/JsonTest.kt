package com.ligh.json

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.json.Json

val json = Json { ignoreUnknownKeys = true
    coerceInputValues = true
}
class JsonTest {

    private val TAG: String = "JsonTest"


//    fun test(){
//        val client = OkHttpClient()
//
//        val getRequest= Request.Builder()
//            .url("https://eq.10jqka.com.cn/open/api/bjquotes_page/index/data")
//            .build()
//
//        try {
//            val response = client.newCall(getRequest).execute()
//            val test = response.body!!.string()
//            val testString = "{\"status_code\":0,\"status_msg\":\"OK\",\"data\":{\"date\":{\"time\":\"2023-11-01\",\"week\":3,\"last_trade_day\":\"2023-10-31\"},\"fast_news\":[\"保荐企业北交所IPO可“拿加分” 存量项目资本运作趋势悄变\",\"前三季度北交所九成公司实现盈利 企稳反弹趋势逐步显现\",\"北交所投资者开户数超601万 中信证券累计开户数居首\",\"北交所新增受理汇兴智造IPO申请\"],\"market_overview\":{\"up\":48,\"plate\":15,\"down\":162,\"cur_trade\":1.129175235E9,\"last_trade\":null},\"range_view\":{\"share\":[{\"date\":\"20230802\",\"total_value\":2.7767169855075E11},{\"date\":\"20230803\",\"total_value\":2.7770305109126E11},{\"date\":\"20230804\",\"total_value\":2.7822129623864E11},{\"date\":\"20230807\",\"total_value\":2.8024986490898E11},{\"date\":\"20230808\",\"total_value\":2.7884374632758E11},{\"date\":\"20230809\",\"total_value\":2.7683368236263E11},{\"date\":\"20230810\",\"total_value\":2.7533779104618E11},{\"date\":\"20230811\",\"total_value\":2.7486084660111002E11},{\"date\":\"20230814\",\"total_value\":2.729351642164E11},{\"date\":\"20230815\",\"total_value\":2.7161948493905E11},{\"date\":\"20230816\",\"total_value\":2.708601199594E11},{\"date\":\"20230817\",\"total_value\":2.6820509983858002E11},{\"date\":\"20230818\",\"total_value\":2.7009421477248E11},{\"date\":\"20230821\",\"total_value\":2.6720272758815E11},{\"date\":\"20230822\",\"total_value\":2.6443353270307E11},{\"date\":\"20230823\",\"total_value\":2.6482290017404E11},{\"date\":\"20230824\",\"total_value\":2.6106256656514E11},{\"date\":\"20230825\",\"total_value\":2.5803115773293E11},{\"date\":\"20230828\",\"total_value\":2.4987301684912E11},{\"date\":\"20230829\",\"total_value\":2.5017742242319E11},{\"date\":\"20230830\",\"total_value\":2.5718100590233E11},{\"date\":\"20230831\",\"total_value\":2.5865769472079E11},{\"date\":\"20230901\",\"total_value\":2.6037287606148E11},{\"date\":\"20230904\",\"total_value\":2.6743356189369E11},{\"date\":\"20230905\",\"total_value\":2.8002541353938E11},{\"date\":\"20230906\",\"total_value\":2.739927732729E11},{\"date\":\"20230907\",\"total_value\":2.7477139744978E11},{\"date\":\"20230908\",\"total_value\":2.7028693783297E11},{\"date\":\"20230911\",\"total_value\":2.6903369411991E11},{\"date\":\"20230912\",\"total_value\":2.7037186214394E11},{\"date\":\"20230913\",\"total_value\":2.7014933609255E11},{\"date\":\"20230914\",\"total_value\":2.6700766862185E11},{\"date\":\"20230915\",\"total_value\":2.6735656837308002E11},{\"date\":\"20230918\",\"total_value\":2.6750547165341998E11},{\"date\":\"20230919\",\"total_value\":2.6822095333583E11},{\"date\":\"20230920\",\"total_value\":2.6811490152992E11},{\"date\":\"20230921\",\"total_value\":2.6208675384413E11},{\"date\":\"20230922\",\"total_value\":2.6082669587766E11},{\"date\":\"20230925\",\"total_value\":2.6373242292717E11},{\"date\":\"20230926\",\"total_value\":2.6265351959942E11},{\"date\":\"20230927\",\"total_value\":2.6123684607129E11},{\"date\":\"20230928\",\"total_value\":2.6305635531018E11},{\"date\":\"20231009\",\"total_value\":2.6528342669192E11},{\"date\":\"20231010\",\"total_value\":2.650666252657E11},{\"date\":\"20231011\",\"total_value\":2.6037916935745004E11},{\"date\":\"20231012\",\"total_value\":2.6196989415672006E11},{\"date\":\"20231013\",\"total_value\":2.6074143373323004E11},{\"date\":\"20231016\",\"total_value\":2.5725688761082004E11},{\"date\":\"20231017\",\"total_value\":2.5454068124784995E11},{\"date\":\"20231018\",\"total_value\":2.5029434747228006E11},{\"date\":\"20231019\",\"total_value\":2.4809655902134006E11},{\"date\":\"20231020\",\"total_value\":2.4579906999994995E11},{\"date\":\"20231023\",\"total_value\":2.4028197514503006E11},{\"date\":\"20231024\",\"total_value\":2.4303726078225006E11},{\"date\":\"20231025\",\"total_value\":2.4583870424808997E11},{\"date\":\"20231026\",\"total_value\":2.460313783254501E11},{\"date\":\"20231027\",\"total_value\":2.6067777512589008E11},{\"date\":\"20231030\",\"total_value\":2.6175004885714005E11},{\"date\":\"20231031\",\"total_value\":2.5951473017744E11},{\"date\":\"20231101\",\"total_value\":0.0}],\"volum\":[{\"date\":\"20230802\",\"flag\":-1,\"total_vol\":8.4428189E7},{\"date\":\"20230803\",\"flag\":-1,\"total_vol\":8.3229996E7},{\"date\":\"20230804\",\"flag\":1,\"total_vol\":8.4689853E7},{\"date\":\"20230807\",\"flag\":-1,\"total_vol\":8.0182587E7},{\"date\":\"20230808\",\"flag\":1,\"total_vol\":8.4544823E7},{\"date\":\"20230809\",\"flag\":1,\"total_vol\":8.7356352E7},{\"date\":\"20230810\",\"flag\":1,\"total_vol\":8.990358E7},{\"date\":\"20230811\",\"flag\":-1,\"total_vol\":8.3444587E7},{\"date\":\"20230814\",\"flag\":-1,\"total_vol\":6.2182507E7},{\"date\":\"20230815\",\"flag\":-1,\"total_vol\":5.0404862E7},{\"date\":\"20230816\",\"flag\":1,\"total_vol\":7.1745931E7},{\"date\":\"20230817\",\"flag\":1,\"total_vol\":7.9225689E7},{\"date\":\"20230818\",\"flag\":1,\"total_vol\":8.9209204E7},{\"date\":\"20230821\",\"flag\":-1,\"total_vol\":7.5608554E7},{\"date\":\"20230822\",\"flag\":1,\"total_vol\":9.3317759E7},{\"date\":\"20230823\",\"flag\":-1,\"total_vol\":9.1327697E7},{\"date\":\"20230824\",\"flag\":1,\"total_vol\":1.02752524E8},{\"date\":\"20230825\",\"flag\":-1,\"total_vol\":1.02542417E8},{\"date\":\"20230828\",\"flag\":1,\"total_vol\":1.25570898E8},{\"date\":\"20230829\",\"flag\":-1,\"total_vol\":1.02924277E8},{\"date\":\"20230830\",\"flag\":-1,\"total_vol\":8.6061678E7},{\"date\":\"20230831\",\"flag\":-1,\"total_vol\":8.0926707E7},{\"date\":\"20230901\",\"flag\":1,\"total_vol\":1.75800493E8},{\"date\":\"20230904\",\"flag\":1,\"total_vol\":3.57769046E8},{\"date\":\"20230905\",\"flag\":-1,\"total_vol\":2.18215768E8},{\"date\":\"20230906\",\"flag\":-1,\"total_vol\":1.47131131E8},{\"date\":\"20230907\",\"flag\":-1,\"total_vol\":1.44322166E8},{\"date\":\"20230908\",\"flag\":-1,\"total_vol\":1.18352665E8},{\"date\":\"20230911\",\"flag\":-1,\"total_vol\":9.1416563E7},{\"date\":\"20230912\",\"flag\":-1,\"total_vol\":8.7734307E7},{\"date\":\"20230913\",\"flag\":1,\"total_vol\":9.4819723E7},{\"date\":\"20230914\",\"flag\":-1,\"total_vol\":9.0109407E7},{\"date\":\"20230915\",\"flag\":-1,\"total_vol\":8.7131575E7},{\"date\":\"20230918\",\"flag\":1,\"total_vol\":9.3775554E7},{\"date\":\"20230919\",\"flag\":-1,\"total_vol\":9.1847442E7},{\"date\":\"20230920\",\"flag\":-1,\"total_vol\":8.0013249E7},{\"date\":\"20230921\",\"flag\":1,\"total_vol\":8.6508676E7},{\"date\":\"20230922\",\"flag\":1,\"total_vol\":9.592601E7},{\"date\":\"20230925\",\"flag\":-1,\"total_vol\":7.4828875E7},{\"date\":\"20230926\",\"flag\":1,\"total_vol\":8.6729267E7},{\"date\":\"20230927\",\"flag\":-1,\"total_vol\":7.4536943E7},{\"date\":\"20230928\",\"flag\":1,\"total_vol\":9.7755499E7},{\"date\":\"20231009\",\"flag\":-1,\"total_vol\":8.3814436E7},{\"date\":\"20231010\",\"flag\":-1,\"total_vol\":7.1323368E7},{\"date\":\"20231011\",\"flag\":-1,\"total_vol\":6.1341405E7},{\"date\":\"20231012\",\"flag\":-1,\"total_vol\":5.8611512E7},{\"date\":\"20231013\",\"flag\":1,\"total_vol\":9.0475836E7},{\"date\":\"20231016\",\"flag\":-1,\"total_vol\":8.3246101E7},{\"date\":\"20231017\",\"flag\":-1,\"total_vol\":6.7581478E7},{\"date\":\"20231018\",\"flag\":-1,\"total_vol\":5.6760895E7},{\"date\":\"20231019\",\"flag\":1,\"total_vol\":6.2492982E7},{\"date\":\"20231020\",\"flag\":-1,\"total_vol\":5.7347956E7},{\"date\":\"20231023\",\"flag\":1,\"total_vol\":6.9245556E7},{\"date\":\"20231024\",\"flag\":-1,\"total_vol\":6.539031E7},{\"date\":\"20231025\",\"flag\":1,\"total_vol\":6.6844572E7},{\"date\":\"20231026\",\"flag\":-1,\"total_vol\":6.0988213E7},{\"date\":\"20231027\",\"flag\":1,\"total_vol\":1.52031397E8},{\"date\":\"20231030\",\"flag\":-1,\"total_vol\":1.2719417E8},{\"date\":\"20231031\",\"flag\":-1,\"total_vol\":1.02907051E8},{\"date\":\"20231101\",\"flag\":-1,\"total_vol\":0.0}]},\"ipo\":{\"ipo_report\":[{\"market\":\"145\",\"stock_code\":\"839258\",\"industry_pe\":29.89,\"industry_belong\":\"通用设备制造业\",\"stock_name\":\"汇兴智造\",\"now_price\":null,\"dy_pe\":25.8022,\"up_rate\":null},{\"market\":\"145\",\"stock_code\":\"833453\",\"industry_pe\":14.87,\"industry_belong\":\"化学原料和化学制品制造业\",\"stock_name\":\"永创医药\",\"now_price\":null,\"dy_pe\":6.0311,\"up_rate\":null},{\"market\":\"145\",\"stock_code\":\"873732\",\"industry_pe\":24.28,\"industry_belong\":\"专用设备制造业\",\"stock_name\":\"佰源装备\",\"now_price\":null,\"dy_pe\":24.2836,\"up_rate\":null}],\"ipo_ready\":[{\"market\":\"145\",\"stock_code\":\"430005\",\"industry_pe\":23.75,\"industry_belong\":\"医药制造业\",\"stock_name\":\"原子高科\",\"now_price\":17.54,\"dy_pe\":16.7789,\"up_rate\":0.2286},{\"market\":\"145\",\"stock_code\":\"430021\",\"industry_pe\":60.04,\"industry_belong\":\"软件和信息技术服务业\",\"stock_name\":\"海鑫科金\",\"now_price\":2.05,\"dy_pe\":null,\"up_rate\":1.99},{\"market\":\"145\",\"stock_code\":\"430046\",\"industry_pe\":60.04,\"industry_belong\":\"软件和信息技术服务业\",\"stock_name\":\"圣 博 润\",\"now_price\":3.23,\"dy_pe\":18.102,\"up_rate\":1.5723}]},\"convert\":{\"convert_report\":null,\"convert_ready\":[{\"market\":\"151\",\"stock_code\":\"430047\",\"industry_pe\":23.75,\"industry_belong\":\"医药制造业\",\"stock_name\":\"诺思兰德\",\"now_price\":14.67,\"dy_pe\":null,\"up_rate\":-2.0694},{\"market\":\"151\",\"stock_code\":\"430090\",\"industry_pe\":60.04,\"industry_belong\":\"软件和信息技术服务业\",\"stock_name\":\"同辉信息\",\"now_price\":2.34,\"dy_pe\":null,\"up_rate\":-1.6807},{\"market\":\"151\",\"stock_code\":\"430139\",\"industry_pe\":32.7,\"industry_belong\":\"计算机、通信和其他电子设备制造业\",\"stock_name\":\"华岭股份\",\"now_price\":10.28,\"dy_pe\":33.2014,\"up_rate\":-2.5592}]},\"funds\":[{\"type\":\"股票型\",\"found_code\":\"014063\",\"found_name\":\"景顺长城专精特新量化优选股票C\",\"now_profit\":\"-30.82\"},{\"type\":\"混合型\",\"found_code\":\"014233\",\"found_name\":\"博时专精特新主题混合C\",\"now_profit\":\"-14.10\"},{\"type\":\"股票型\",\"found_code\":\"014186\",\"found_name\":\"招商专精特新股票C\",\"now_profit\":\"-14.08\"}]},\"trace_id\":\"0ac96bdc169876953315181601\"}"
//            val jsonData = json.decodeFromString<JsonResponse>(testString)
//            Log.i(TAG, "test: $jsonData")
//            val bean = transBean(jsonData)
//            Log.i(TAG, "test: $bean")
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//
//
//    }


    fun test1(){

        val dates: MutableList<MyDate> = ArrayList()
        for (i in 0..200) {
            dates.add(MyDate(2024, 4, 25))
        }



        // 使用Gson将List<MyDate>转换为JSON数组
        val gson = GsonBuilder().setPrettyPrinting().create()
        val json = gson.toJson(dates)


        // 使用Gson自定义适配器
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(List::class.java, MyDateListAdapter())
        val gsonParse = gsonBuilder.create()




        val thread = object :Thread(){
            override fun run() {
                while (true){
                    while (true){
                        sleep(500)
                        val dateList: List<MyDate> = gsonParse.fromJson(json, object : TypeToken<List<MyDate>>() {}.type)

                        Bean(dateList)
                        dateList.forEachIndexed { index, myDate ->
                            Log.i("JsonTest", "test: $myDate ${myDate.index}")
                        }
                    }
                }
            }
        }
        thread.start()
    }


}
