<template>
  <st-dialog
    v-if="open"
    title="选择联系人"
    :height="'640px'"
    @close="open = false"
    @save="submitForm"
  >
    <div class="search">
      <el-form ref="queryForm" :model="queryParams" :inline="true" label-width="100px">
        <el-row :gutter="24">
          <el-col :span="24" :offset="1">
            <el-form-item label="联系人名称：" prop="contactsName">
              <el-input
                v-model="queryParams.contactsName"
                placeholder="请输入联系人名称"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="联系人编号：" prop="contactsId">
              <el-input
                v-model="queryParams.contactsId"
                placeholder="请输入联系人编号"
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
      <el-table v-loading="loading" height="430" border :data="contactsList">
        <st-empty />
        <el-table-column width="55" align="center">
          <template #default="scope">
            <el-radio v-model="contactsId" :label="scope.row.contactsId" @change="handlerRowChange(scope.row)">{{ "" }}</el-radio>
          </template>
        </el-table-column>
        <el-table-column label="姓名" align="center" prop="contactsName" />
        <el-table-column label="编号" align="center" prop="contactsId" />
        <el-table-column label="手机" align="center" prop="mobile" />
        <el-table-column label="性别" align="center" prop="sex" />
        <el-table-column label="邮箱" align="center" prop="mailbox" />
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
import { listContacts } from "@/api/crm/contacts";
export default {
  name: "SelectContacts",
  data() {
    return {
      contactsId: "",
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
        contactsName: null,
        contactId: null
      },
      // 表单参数
      form: {}
    };
  },
  methods: {
    show() {
      this.contactsId = "";
      this.open = true;
      this.getList();
    },

    handlerRowChange(selection) {
      this.ids = selection;
    },
    getList() {
      this.loading = true;
      listContacts(this.queryParams).then(response => {
        this.contactsList = response.rows;
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
