# InputCode-master

用于输入验证码,可以设置输入框的个数以及其他特性

## Import


**Step 1. 在项目根build.gradle文件中增加JitPack仓库依赖。** 
```
    allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```
Step 2. Add the dependency (api/implementation)
```
    dependencies {
	        implementation 
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
