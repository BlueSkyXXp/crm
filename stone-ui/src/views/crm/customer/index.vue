<template>
  <!-- 已废弃 -->
  <el-tabs v-model="activeName" type="border-card" @tab-click="handleClick">
    <!-- 主管能看到所有组员每个客户的跟进情况 -->
    <!-- <el-tab-pane label="团队客户" name="first">
      <group-list></group-list>
    </el-tab-pane> -->
    <!-- 区长（大主管）可以自由分配任务给任何人 -->
    <!-- <el-tab-pane label="待分配客户" name="third">
      <group-list></group-list>
    </el-tab-pane>
    <el-tab-pane label="离职人员客户" name="fourth">
      <group-list></group-list>
    </el-tab-pane> -->
    <el-tab-pane label="我的客户" name="my">
      <!-- 包括再分配客户和我的客户 -->
      <all />
    </el-tab-pane>
    <!-- <el-tab-pane label="今日待跟进的" name="third">
      <all></all>
    </el-tab-pane>
    <el-tab-pane label="今日已联系的" name="fourth">
      <all></all>
    </el-tab-pane> -->
    <el-tab-pane label="再分配客户" name="again">
      <all />
    </el-tab-pane>
  </el-tabs>

</template>

<script>
import all from "./all.vue";
import groupList from "./groupList.vue";
export default {
  name: "Customer",
  components: {
    all, groupList
  },
  data() {
    return {
      activeName: "my"
    };
  },
  created() {
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    }
  }
};
</script>

<style scoped lang="scss">
    .el-tabs{
      margin:10px !important;
      border: 1px solid #eaeffc;
      height: calc(100vh - 104px);
      //overflow: auto;
      box-shadow: 0px 0px 12px 0px rgba(188, 188, 188, 0.36);
    }
    ::v-deep .el-tabs__content{
      padding:0px !important;
    }
</style>
