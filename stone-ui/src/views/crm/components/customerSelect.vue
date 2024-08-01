<template>
  <st-dialog
    v-if="open"
    title="选择客户"
    :height="'640px'"
    @close="open = false"
    @save="submitForm"
  >
    <div class="search">
      <el-form ref="queryForm" :model="queryParams" :inline="true" label-width="100px">
        <el-row :gutter="24">
          <el-col :span="24" :offset="1">
            <el-form-item label="产品名称：" prop="productName">
              <el-input
                v-model="queryParams.productName"
                placeholder="请输入产品名称"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="产品编码：" prop="num">
              <el-input
                v-model="queryParams.num"
                placeholder="请输入产品编码"
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
      <el-table v-loading="loading" height="430" border :data="customerList">
        <st-empty />
        <el-table-column width="55" align="center">
          <template #default="scope">
            <el-radio v-model="customerId" :label="scope.row.customerId" @change="handleRowChange(scope.row)">{{ "" }}</el-radio>
          </template>
        </el-table-column>
        <el-table-column label="客户名称" align="center" prop="customerName" />
        <el-table-column label="客户性质" align="center" prop="natureDisp" />
        <el-table-column label="客户来源" align="center" prop="sourceDisp" />
        <el-table-column label="客户行业" align="center" prop="industryDisp" />
        <el-table-column label="客户分级" align="center" prop="gradeDisp" />
        <el-table-column label="客户阶段" align="center" prop="stageDisp" />
        <el-table-column label="客户官网" align="center" prop="website" />
        <el-table-column label="客户类型" align="center" prop="typeDisp" />
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
import { listCustomer } from "@/api/crm/customer";
export default {
  name: "SelectCustomer",
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
      customerList: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        productName: null,
        num: null
      },
      // 表单参数
      form: {},
      customerId: ""
    };
  },
  methods: {
    show() {
      this.open = true;
      this.customerId = "";
      this.getList();
    },
    getList() {
      this.loading = true;
      listCustomer(this.queryParams).then(response => {
        this.customerList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
    handleRowChange(selection) {
      this.ids = selection;
    },
    submitForm() {
      this.$emit("selected", this.ids);
      this.open = false;
    }
  }

};
</script>
<style scoped lang="scss">
.el-radio__label {
  font-size: 14px;
  padding-left: 0px;
}
</style>
