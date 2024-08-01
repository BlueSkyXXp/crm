<template>
  <st-dialog
    v-if="open"
    title="选择回款计划"
    :height="'650px'"
    confirm-button-text="确定"
    @close="open = false"
    @save="submitForm"
  >
    <div class="search">
      <el-form ref="queryForm" :model="queryParams" :inline="true" label-width="100px">
        <el-row :gutter="24">
          <el-col :span="24" :offset="1">
            <el-form-item label="合同名称：" prop="contractName">
              <el-input
                v-model="queryParams.contractName"
                placeholder="请输入合同名称"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
              <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </div>
    <div class="content">
      <el-table v-loading="loading" height="440" border :data="receivablesPlanList">
        <st-empty />
        <el-table-column width="55" align="center">
          <template #default="scope">
            <el-radio v-model="planId" :label="scope.row.planId" @change="handlerRowChange(scope.row)">{{ "" }}</el-radio>
          </template>
        </el-table-column>
        <el-table-column width="170" label="计划编号" align="center" prop="planId" />
        <el-table-column label="计划金额" align="center" prop="money" />
        <el-table-column label="回款日期" align="center" prop="returnDate" />
        <el-table-column label="回款方式" align="center" prop="returnTypeDisp" />
        <el-table-column width="170" label="合同ID" align="center" prop="contractId" />
        <el-table-column label="合同名称" align="center" prop="contractName" />
      </el-table>
    </div>
    <div class="footer" style="margin-top: 10px;">
      <pagination
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
    </div>
  </st-dialog>
</template>
<script>
import { listPlan } from "@/api/crm/plan";
export default {
  name: "SelectReceivablesPlan",
  data() {
    return {
      tableHeight: undefined,
      open: false,
      loading: false,
      // 选中数组
      ids: [],
      // 总条数
      total: 0,
      // 回款计划数据
      receivablesPlanList: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        contractName: null
      },
      // 表单参数
      form: {},
      planId: "",
      customerId: ""
    };
  },
  methods: {
    show(planId, customerId) {
      this.planId = planId;
      this.customerId = customerId;
      this.open = true;
      this.getList();
    },
    getList() {
      this.loading = true;
      // 只能查询到当前客户的回款计划并且计划状态为0未完成
      this.$set(this.queryParams, "customerId", this.customerId);
      this.$set(this.queryParams, "status", "0");
      listPlan(this.queryParams).then(response => {
        this.receivablesPlanList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    handlerRowChange(selection) {
      this.ids = selection;
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection;
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    submitForm() {
      if (this.ids.length != 0) {
        this.$emit("selected", this.ids);
        this.open = false;
      } else {
        this.$modal.msgError("请选择回款计划");
      }
    }
  }

};
</script>
<style scoped lang="scss">

</style>
