# InputCode-master

用于输入验证码,可以设置输入框的个数以及其他特性


![](https://github.com/Fan-cn/InputCode-master/blob/master/app/gif/screen.gif)


## Import


**Step 1. 在项目根build.gradle文件中增加jcenter仓库依赖。**
```
    allprojects {
		repositories {
			...
			jcenter()
		}
	}
```

**Step 2. Add the dependency (api/implementation)**
```
    dependencies {
	        implementation 'com.fan.corelibrary:1.0.0'
	}
```

## Features

属性 | 含义
------------ | -------------
code_box            |显示输入框的数量
code_box_width      |每个输入框的宽度
code_box_height     |每个输入框的高度
code_box_v_padding  |输入框与上边和下边的间距
code_box_h_padding  |输入框之间的横向间距
code_box_bg_focus   |输入框未选中时的背景
code_box_bg_normal  |输入框选中时的背景
code_text_color     |输入文字的颜色
code_text_size      |输入文字的大小
code_input_type     |输入文字的类型
code_is_show_back   |是否显示自定义背景(默认显示)



## Usage

xml:


```
<!--使用默认UI属性-->
    <com.fan.corelibrary.InputCode
        android:id="@+id/edit_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        />

<!--设置输入框数量、输入类型、宽度、高度-->
<com.fan.corelibrary.InputCode
        android:id="@+id/edit_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        fan:code_box="4"
        fan:code_input_type="number"
        fan:code_box_width="50dp"
        fan:code_box_height="50dp"
        />
```

# MIT

MIT License

Copyright 2018 Fan

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.