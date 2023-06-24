package com.ligh.other.serialization

import android.os.Parcel
import android.os.Parcelable

/**
 * 原始方式Parcelable，google推荐，Android内部使用，高效
 * 本质： 将对象解析为intent可以解析的基本类型，存储于共享内存地址中，主要用于进程通信
 */
class Student() :Parcelable {

    var  name = ""
    var sex = 1
    var address = ""

    /**
     * 注意按照顺序
     */
    constructor(parcel: Parcel) : this() {
        name = parcel.readString()?:"empty"
        sex = parcel.readInt()
        address = parcel.readString()?:"empty"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(sex)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Student> {
        override fun createFromParcel(parcel: Parcel): Student {
            return Student(parcel)
        }

        override fun newArray(size: Int): Array<Student?> {
            return arrayOfNulls(size)
        }
    }

}


//@Parcelize //根据构造方法中的变量自动实现Parcelable，
//data class StudentPlus(var name = ""):Parcelable