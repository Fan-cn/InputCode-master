# InputCode-master

用于输入验证码,可以设置输入框的个数以及其他特性

## Import


**Step 1. 在项目根build.gradle文件中增加JitPack仓库依赖。** 
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