<template>
  <div class="title">
    <div v-show="isIcon" class="title-icon" />
    <div class="text">{{ title }}</div>
  </div>
</template>
<script>
export default {
  name: "Title",
  props: {
    title: {
      type: String,
      default: ""
    },
    isIcon: {
      type: Boolean,
      default: true
    }
  },
  data() {
    return {
    };
  }
};
</script>
<style scoped lang="scss">
.title {
  font-size: 16px;
  font-weight: 700;
  // border-bottom: 1px solid #e6ebf5;
  box-sizing: border-box;
  display: flex;
  padding: 5px;
  align-items: center;
  margin-bottom: 5px;
  background-color: #e6ebf5;
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
</style>
