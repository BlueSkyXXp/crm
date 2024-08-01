<template>
  <div class="form-page-container">
    <div class="form-panel-container">
      <div class="form-title">
        <div class="form-title-icon" />
        <div class="form-text">{{ title }}</div>
        <div class="form-btn-area">
          <slot name="btm" />
        </div>
      </div>
      <div class="form-content-container">
        <slot name="content" />
      </div>
      <div class="form-footer-container">
        <slot name="footer" />
      </div>
    </div>
  </div>
</template>
<script >
export default {
  name: "TablePanel",
  props: {
    title: String
  },
  data() {
    return {
    };
  }
};
</script>
<style scoped lang="scss">
.form-page-container {
  width: 100%;
  padding: 10px;
  height: calc(100vh - 272px);
  overflow: auto;
  .form-panel-container{
    height:100%;
    .form-title {
      font-size: 16px;
      font-weight: 700;
      border-bottom: 1px solid #e6ebf5;
      box-sizing: border-box;
      display: flex;
      padding: 0 5px 10px;
      align-items: center;
      .form-btn-area {
        -webkit-box-flex: 1;
        flex: 1;
        text-align: right;
        margin-right: 0px;
      }
      .form-title-icon {
        width: 6px;
        height: 20px;
        background-color: #409EFF;
        background-size: 100% 100%;
        margin: 1px 7px 0px 7px;
      }
    }
    .form-content-container {
      /*background: #f2f2f2;
      margin-top: 5px;*/
      padding-left: 10px;
      padding-right: 10px;

    }
    .form-footer-container {
      background: #fff;
      height: 42px;
      position: absolute;
      bottom: 0px;
      right: 15px;
    }
  }

}

::v-deep .el-form-item{
  margin-bottom: 14px;
}

.el-form{
  text-align: center;
  padding: 24px 10px 10px 10px;
}

::v-deep .el-table__empty-block{
  border-bottom: 1px solid #e6ebf5;
}
</style>
