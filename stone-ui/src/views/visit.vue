<template>
  <div class="visit-mobile">
    <img :src="require('@/assets/images/zyr.png')" alt="">
    <el-form ref="form" :model="form" :rules="rules" label-position="top" label-width="120px">
      <el-form-item label="客户姓名" prop="customerName">
        <el-input v-model="form.customerName" placeholder="请输入客户姓名" />
      </el-form-item>
      <el-form-item label="接待顾问姓名" prop="userName">
        <el-input v-model="form.userName" placeholder="请输入接待顾问姓名" />
      </el-form-item>
      <el-form-item label="客户手机号" prop="phone">
        <el-input v-model="form.phone" placeholder="请输入客户手机号" />
      </el-form-item>
      <el-form-item label="备注信息" prop="remark">
        <el-input v-model="form.remark" type="textarea" autosize placeholder="请输入备注信息" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import { addVisitByQRCode } from "@/api/crm/visit";
export default {
  name: "VisitMobile",
  data() {
    return {
      form: {}
    };
  },
  methods: {
    /** 表单重置 */
    reset() {
      this.form = {};
      this.resetForm("form");
    },
    /** 提交 */
    onSubmit() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          // todo 免验证接口
          console.log(this.form);
          addVisitByQRCode(this.form).then((response) => {
            this.$modal.msgSuccess("新增成功");
            this.reset();
          });
        } else {
          return false;
        }
      });
    }
  }
};
</script>
<style lang="scss" scoped>
.visit-mobile {
  // width: 800px;
  max-width: 100%;
  // margin: auto 20px;
  font-size: 28px;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  img {
    position: absolute;
    width: 100%;
    top: 6px;
  }
  .el-form {
    width: 80%;
    max-width: 100%;
  }
  .el-form-item {
    margin-bottom: 6px;
  }
  .el-button--primary {
    width: 100%;
    font-size: 28px;
    background-color: rgb(6, 58, 147);
    border-color: rgb(6, 58, 147);
  }
  ::v-deep .el-form--label-top .el-form-item__label {
    padding: 0;
    font-size: 22px;
  }
}
</style>
