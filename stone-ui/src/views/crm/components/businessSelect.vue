<template>
  <st-dialog
    v-if="open"
    title="选择商机"
    :height="'640px'"
    confirm-button-text="确定"
    @close="open = false"
    @save="submitForm"
  >
    <div class="search">
      <el-form ref="queryForm" :model="queryParams" :inline="true" label-width="100px">
        <el-row :gutter="24">
          <el-col :span="24" :offset="1">
            <el-form-item label="商机名称：" prop="businessName">
              <el-input
                v-model="queryParams.businessName"
                placeholder="请输入商机名称"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="商机编号：" prop="businessId">
              <el-input
                v-model="queryParams.businessId"
                placeholder="请输入商机编号"
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
      <el-table v-loading="loading" height="430" border :data="businessList">
        <st-empty />
        <el-table-column width="55" align="center">
          <template #default="scope">
            <el-radio v-model="businessId" :label="scope.row.businessId" @change="handlerRowChange(scope.row)">{{ "" }}</el-radio>
          </template>
        </el-table-column>
        <el-table-column label="商机名称" align="center" prop="businessName" />
        <el-table-column label="商机编号" align="center" prop="businessId" />
        <el-table-column label="商机金额" align="center" prop="money" />
        <el-table-column label="商机阶段" align="center" prop="statusIdDisp" />
        <el-table-column label="整单折扣" align="center" prop="discountRate" />
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
import { listBusiness } from "@/api/crm/business";
export default {
  name: "SelectBusiness",
  data() {
    return {
      businessId: "",
      tableHeight: undefined,
      open: false,
      loading: false,
      // 选中数组
      ids: [],
      // 总条数
      total: 0,
      // 联系人表格数据
      contactsList: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        businessName: null,
        businessId: null
      },
      // 表单参数
      form: {}
    };
  },
  methods: {
    show() {
      this.businessId = "";
      this.open = true;
      this.getList();
    },

    handlerRowChange(selection) {
      this.ids = selection;
    },
    getList() {
      this.loading = true;
      listBusiness(this.queryParams).then(response => {
        this.businessList = response.rows;
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
    submitForm() {
      this.$emit("selected", this.ids);
      this.open = false;
    }
  }

};
</script>
<style scoped lang="scss">

</style>
