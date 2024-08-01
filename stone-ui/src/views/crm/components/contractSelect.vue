<template>
  <st-dialog
    v-if="open"
    title="选择合同"
    :height="'650px'"
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
      <el-table v-loading="loading" height="440" border :data="contractList">
        <st-empty />
        <el-table-column width="55" align="center">
          <template #default="scope">
            <el-radio v-model="contractId" :label="scope.row.contractId" @change="handlerRowChange(scope.row)">{{ "" }}</el-radio>
          </template>
        </el-table-column>
        <el-table-column label="合同名称" align="center" prop="contractName" />
        <el-table-column label="合同金额" align="center" prop="money" />
        <el-table-column label="合同类型" align="center" prop="typesDisp" />
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
import { listContract } from "@/api/crm/contract";
export default {
  name: "SelectContract",
  data() {
    return {
      tableHeight: undefined,
      open: false,
      loading: false,
      // 选中数组
      ids: [],
      // 总条数
      total: 0,
      // 产品表格数据
      contractList: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        contractName: null
      },
      // 表单参数
      form: {},
      contractId: ""
    };
  },
  methods: {
    show() {
      this.open = true;
      this.getList();
    },
    getList() {
      this.loading = true;
      listContract(this.queryParams).then(response => {
        this.contractList = response.rows;
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
      this.$emit("selected", this.ids);
      this.open = false;
    }
  }

};
</script>
<style scoped lang="scss">

</style>
