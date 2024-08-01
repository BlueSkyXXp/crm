<template>
  <st-table-panel>
    <div slot="btn" style="text-align: right;">
      <!-- <el-button
        type="primary"
        plain
        icon="el-icon-plus"
        @click="handleAdd"
        v-hasPermi="['crm:contract:add']"
      >新增</el-button> -->
    </div>
    <div slot="content">
      <el-divider />
      <el-table v-loading="loading" :show-header="false" :data="contactsList" @selection-change="handleSelectionChange">
        <!-- <el-table-column label="记录ID" align="center" prop="id" /> -->
        <el-table-column label="" align="center" prop="customerId">
          <template slot-scope="{row}">
            {{ row.createTime }} 从 {{ row.sourcePlat }} {{ row.targetPlat=='公共池'?'加入 ':'分配给 ' }} {{ row.targetPlat }}，操作人{{ row.userName }}</template>
        </el-table-column>
        <!-- <el-table-column label="客户来源" align="center" prop="sourcePlat" />
        <el-table-column label="客户去向" align="center" prop="targetPlat" />
        <el-table-column label="操作者" align="center" prop="userName" />
        <el-table-column label="操作时间" align="center" prop="createTime" /> -->
      </el-table>
    </div>
    <div slot="footer">
      <pagination
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
    </div>
  </st-table-panel>
</template>

<script>
import { listCustomer_flow } from "@/api/crm/flow";
import noData from "@/assets/img/no_data.png";
export default {
  name: "Contacts",
  props: {
    customerForm: {
      type: Object,
      default: function() {
        return {};
      }
    }
  },
  data() {
    return {
      noData: noData,
      tableHeight: undefined,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 联系人表格数据
      contactsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
  },
  methods: {
    /** 查询联系人列表 */
    getList() {
      this.loading = true;
      this.$set(this.queryParams, "customerId", this.customerForm.id);
      listCustomer_flow(this.queryParams).then(response => {
        this.contactsList = response.rows;
        this.total = response.total;
        this.loading = false;
        this.$nextTick(() => {
          this.tableHeight = this.getTableHeight();
        });
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {};
      this.resetForm("form");
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
      this.ids = selection.map(item => item.contactsId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    }
  }
};
</script>
<style scoped lang="scss">
.page-container{
  height: calc(100vh - 273px) ;
}
::v-deep .el-radio{
  margin-right: 5px;
}
.el-select {
  width : 100%;
}
.el-divider {
  padding: 0;
  margin: 0;
}
</style>
