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
          <a-input v-decorator="[ 'userId', validatorRules.userId]" placeholder="请输入用户ID"></a-input>
        </a-form-item>
        <a-form-item label="服务类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'subCategoryId', validatorRules.subCategoryId]" placeholder="请输入服务类型"></a-input>
        </a-form-item>
        <a-form-item label="上门提货时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择上门提货时间" v-decorator="[ 'takeGoodsTime', validatorRules.takeGoodsTime]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="预估重量" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'estimatWeight', validatorRules.estimatWeight]" placeholder="请输入预估重量" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="图片" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'image', validatorRules.image]" placeholder="请输入图片"></a-input>
        </a-form-item>
        <a-form-item label="备注描述" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'memo', validatorRules.memo]" placeholder="请输入备注描述"></a-input>
        </a-form-item>
        <a-form-item label="周期" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'cycle', validatorRules.cycle]" placeholder="请输入周期" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="创建人" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'createBy', validatorRules.createBy]" placeholder="请输入创建人"></a-input>
        </a-form-item>
        <a-form-item label="创建时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择创建时间" v-decorator="[ 'createTime', validatorRules.createTime]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="最后执行时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择最后执行时间" v-decorator="[ 'lastupdateTime', validatorRules.lastupdateTime]" :trigger-change="true" style="width: 100%"/>
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
    name: "RecyclePlanOrderModal",
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
          userId: {rules: [
            {required: true, message: '请输入用户ID!'},
          ]},
          subCategoryId: {rules: [
            {required: true, message: '请输入服务类型!'},
          ]},
          takeGoodsTime: {rules: [
          ]},
          estimatWeight: {rules: [
          ]},
          image: {rules: [
          ]},
          memo: {rules: [
          ]},
          cycle: {rules: [
          ]},
          createBy: {rules: [
          ]},
          createTime: {rules: [
          ]},
          lastupdateTime: {rules: [
          ]},
        },
        url: {
          add: "/recyclecategory/recyclePlanOrder/add",
          edit: "/recyclecategory/recyclePlanOrder/edit",
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
          this.form.setFieldsValue(pick(this.model,'userId','subCategoryId','takeGoodsTime','estimatWeight','image','memo','cycle','createBy','createTime','lastupdateTime'))
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
        this.form.setFieldsValue(pick(row,'userId','subCategoryId','takeGoodsTime','estimatWeight','image','memo','cycle','createBy','createTime','lastupdateTime'))
      },

      
    }
  }
</script>