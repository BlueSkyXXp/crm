<template>
  <div class="page-container">
    <div class="panel-container">
      <div class="title">
        <div class="title-icon" />
        <div class="text">{{ title }}</div>
        <div class="btn-area">
          <slot name="btm" />
        </div>
      </div>
      <div class="search-container">
        <slot name="search" />
      </div>
      <div class="content-container">
        <slot name="content" />
      </div>
      <div class="footer-container">
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
.page-container {
  /*padding: 10px;*/
  width: 100%;
  //border: 1px solid #eaeffc;
  padding: 10px;
  height: calc(100vh - 84px);
  overflow: auto;
  //box-shadow: 0px 0px 12px 0px rgba(188, 188, 188, 0.36);
  .panel-container{
    height:100%;
    .title {
      font-size: 16px;
      font-weight: 700;
      border-bottom: 1px solid #e6ebf5;
      box-sizing: border-box;
      display: flex;
      padding: 0 5px 10px;
      align-items: center;
      .btn-area {
        -webkit-box-flex: 1;
        flex: 1;
        text-align: right;
        margin-right: 0px;
      }
      .title-icon {
        width: 6px;
        height: 20px;
        background-color: #409EFF;
        background-size: 100% 100%;
        margin: 1px 7px 0px 7px;
      }
    }
    .content-container {
      /*background: #f2f2f2;
      margin-top: 5px;*/
      padding-left: 10px;
      padding-right: 10px;
      padding-bottom: 50px;

    }
    .footer-container {
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
  // text-align: center;
  padding: 24px 10px 10px 10px;
}

::v-deep .el-table__empty-block{
  border-bottom: 1px solid #e6ebf5;
}
</style>
