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

        <a-form-item label="订单编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'orderCode', validatorRules.orderCode]" placeholder="请输入订单编号"></a-input>
        </a-form-item>
        <a-form-item label="用户ID" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'userId', validatorRules.userId]" placeholder="请输入用户ID"></a-input>
        </a-form-item>
        <a-form-item label="回收人员" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'recycleUserId', validatorRules.recycleUserId]" placeholder="请输入回收人员"></a-input>
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
        <a-form-item label="实际重量" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'actualWeight', validatorRules.actualWeight]" placeholder="请输入实际重量" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="凭证" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'voucher', validatorRules.voucher]" placeholder="请输入凭证"></a-input>
        </a-form-item>
        <a-form-item label="需支付价格" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'price', validatorRules.price]" placeholder="请输入需支付价格"></a-input>
        </a-form-item>
        <a-form-item label="完成时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择完成时间" v-decorator="[ 'compleTime', validatorRules.compleTime]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="图片" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'image', validatorRules.image]" placeholder="请输入图片"></a-input>
        </a-form-item>
        <a-form-item label="备注描述" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'memo', validatorRules.memo]" placeholder="请输入备注描述"></a-input>
        </a-form-item>
        <a-form-item label="订单类型 （1 单次 2 定期）" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'type', validatorRules.type]" placeholder="请输入订单类型 （1 单次 2 定期）" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="创建人" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'createBy', validatorRules.createBy]" placeholder="请输入创建人"></a-input>
        </a-form-item>
        <a-form-item label="创建时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择创建时间" v-decorator="[ 'createTime', validatorRules.createTime]" :trigger-change="true" style="width: 100%"/>
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
    name: "RecycleUserOrderModal",
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
          orderCode: {rules: [
            {required: true, message: '请输入订单编号!'},
          ]},
          userId: {rules: [
            {required: true, message: '请输入用户ID!'},
          ]},
          recycleUserId: {rules: [
            {required: true, message: '请输入回收人员!'},
          ]},
          subCategoryId: {rules: [
            {required: true, message: '请输入服务类型!'},
          ]},
          takeGoodsTime: {rules: [
          ]},
          estimatWeight: {rules: [
          ]},
          actualWeight: {rules: [
          ]},
          voucher: {rules: [
          ]},
          price: {rules: [
          ]},
          compleTime: {rules: [
          ]},
          image: {rules: [
          ]},
          memo: {rules: [
          ]},
          type: {rules: [
          ]},
          createBy: {rules: [
          ]},
          createTime: {rules: [
          ]},
        },
        url: {
          add: "/recyclecategory/recycleUserOrder/add",
          edit: "/recyclecategory/recycleUserOrder/edit",
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
          this.form.setFieldsValue(pick(this.model,'orderCode','userId','recycleUserId','subCategoryId','takeGoodsTime','estimatWeight','actualWeight','voucher','price','compleTime','image','memo','type','createBy','createTime'))
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
        this.form.setFieldsValue(pick(row,'orderCode','userId','recycleUserId','subCategoryId','takeGoodsTime','estimatWeight','actualWeight','voucher','price','compleTime','image','memo','type','createBy','createTime'))
      },

      
    }
  }
</script>