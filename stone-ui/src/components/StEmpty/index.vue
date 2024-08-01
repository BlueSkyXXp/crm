<template>
  <div slot="empty">
    <el-empty :image="noData" :image-size="200" />
  </div>
</template>
<script>
import noData from "@/assets/img/no_data.png";
export default {
  data() {
    return {
      noData
    };
  }
};
</script>
<style scoped lang="scss">

</style>
