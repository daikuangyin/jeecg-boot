<template>
  <a-modal
    :title="title"
    :width="width"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item label="用户ID" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'name', validatorRules.name]" placeholder="请输入用户ID"></a-input>
        </a-form-item>
        <a-form-item label="用户类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'type', validatorRules.type]" placeholder="请输入用户类型 1 普通用户 2 回收员" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="电话号码" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'phone', validatorRules.phone]" placeholder="请输入电话号码"></a-input>
        </a-form-item>
        <a-form-item label="身份证号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'idCard', validatorRules.idCard]" placeholder="请输入身份证号"></a-input>
        </a-form-item>
        <a-form-item label="地址" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'address', validatorRules.address]" placeholder="请输入地址"></a-input>
        </a-form-item>
        <a-form-item label="图片" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'image', validatorRules.image]" placeholder="请输入图片"></a-input>
        </a-form-item>
      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JDate from '@/components/jeecg/JDate'  

  export default {
    name: "RecycleUserModal",
    components: { 
      JDate,
    },
    data () {
      return {
        form: this.$form.createForm(this),
        title:"操作",
        width:800,
        visible: false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
          name: {rules: [
            {required: true, message: '请输入用户ID!'},
          ]},
          type: {rules: [
            {required: true, message: '请输入用户类型 1 普通用户 2 回收员!'},
          ]},
          phone: {rules: [
            {required: true, message: '请输入电话号码!'},
          ]},
          idCard: {rules: [
            {required: true, message: '请输入身份证号!'},
          ]},
          address: {rules: [
          ]},
          image: {rules: [
          ]},
          createBy: {rules: [
          ]},
          createTime: {rules: [
          ]},
        },
        url: {
          add: "/recycle/recycleUser/add",
          edit: "/recycle/recycleUser/edit",
        }
      }
    },
    created () {
    },
    methods: {
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'name','type','phone','idCard','address','image','createBy','createTime'))
        })
      },
      close () {
        this.$emit('close');
        this.visible = false;
      },
      handleOk () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            let formData = Object.assign(this.model, values);
            console.log("表单提交数据",formData)
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })
          }
         
        })
      },
      handleCancel () {
        this.close()
      },
      popupCallback(row){
        this.form.setFieldsValue(pick(row,'name','type','phone','idCard','address','image','createBy','createTime'))
      },

      
    }
  }
</script>