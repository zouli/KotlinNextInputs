# KotlinNextInputs
NextInputs的Kotlin扩展  

## 支持Kotlin的lambda调用
```
fun doValidate(): Boolean = nextInput {
    //设置MessageDisplay
    messageDisplay = if (rb_show_snackbar.isChecked) AndroidSnackbarMessageDisplay() else AndroidToastMessageDisplay()

    //子项目至少选择一项
    add(rg_1.nextInput, StaticScheme.Required().msg("请在rb_1,rb_2,rb_3中选择一个"))

    //子项目至少选择一项
    add(gl_1.nextInput(checkableId = R.id.cb_1), StaticScheme.Required().msg("请至少选择一个cb_1"))

    //至少输入一项
    add(et_1.findOnceSatisfy(et_1.nextInput, et_2.nextInput), StaticScheme.Required().msg("文本框至少输入一项"))

    //当前有值时前项必须输入
    add(et_2.nextInput, SchemeExtend.RequiredAndPreviousRequired(et_1.lazyLoader))

    //正则表达式检查
    add(et_1.nextInput, SchemeExtend.Regex(SchemeExtend.PASSWORD_8DL_REGEX).msg("请输入8位英文+数字"))
}.test()
```

### 支持的Widget
+ TextView.nextInput
+ EditText.nextInput
+ RadioButton.nextInput
+ CheckBox.nextInput
+ ToggleButton.nextInput
+ RatingBar.nextInput
+ CompoundButton.nextInput
+ RadioGroup.nextInput
+ ViewGroup.nextInput(childMinCount: Int = -1, checkableId: Int = -1)
   > childMinCount >= 0 时判断是否包括childMinCount个子项目  
   > checkableId > 0 时判断指定id的子项目是否被选中  
+ View.nextTagInput(id: Int)
   > id 需要取得的Tag的ID

### LazyLoaders
+ EditText.lazyLoader
+ TextView.lazyLoader

## MessageDisplay实现
+ AndroidToastMessageDisplay
+ AndroidSnackbarMessageDisplay
+ BlankMessageDisplay

## Scheme扩展
### RequiredAndPreviousRequired(lazyLoader: TextLazyLoader)
当前有值时前项必须输入  
  > lazyLoader 前一项的控件

### Regex(regex: String)
正则表达式检查  
  > 正则表达式

## WidgetAccess扩展
### findRadioGroup(viewId: Int)
取得RadioGroup子项目是否已选择  
  > viewId 子项目ID  

### findTag(viewId: Int, tagId: Int)
取得View的Tag值  

  > viewId 控件ID  
  > tagId Tag的ID  

### findGridLayoutSelectable(gridLayoutId: Int, checkableId: Int)
取得GridLayout已选择Child
  > gridLayoutId GridLayout的ID  
  > checkableId 被选择的子项目ID  
  
### findGridLayoutHasChild(gridLayoutId: Int, childMinCount: Int)
取得GridLayout是否包含Child  
  > gridLayoutId GridLayout的ID  
  > childMinCount 最少包含子项目数量  
  
### findOnceSatisfy(viewId: Int, vararg inputs: Input)
取得最少一项为true
