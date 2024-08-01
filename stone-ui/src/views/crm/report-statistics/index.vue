<template>
  <TablePanel title="来访登记统计">
    <div slot="btm">
      <el-button
        v-hasPermi="['crm:visit:add']"
        type="primary"
        plain
        icon="el-icon-plus"
        @click="handleAdd"
      >新增</el-button>
      <el-button
        v-hasPermi="['crm:visit:edit']"
        type="success"
        plain
        icon="el-icon-edit"
        :disabled="single"
        @click="handleUpdate"
      >修改</el-button>
      <!-- <el-button
              type="danger"
              plain
              icon="el-icon-delete"
              :disabled="multiple"
              @click="handleDelete"
              v-hasPermi="['crm:visit:remove']"
          >删除</el-button> -->
      <el-button
        v-hasPermi="['crm:visit:export']"
        type="warning"
        plain
        icon="el-icon-download"
        @click="handleExport"
      >导出</el-button>
      <right-toolbar :show-search.sync="showSearch" @queryTable="getList" />
    </div>
    <div slot="content">
      <el-form v-show="showSearch" ref="queryForm" :model="queryParams" :inline="true" label-width="68px">
        <!-- <el-form-item label="部门" prop="deptId">
        <el-input
          v-model="queryParams.deptId"
          placeholder="请选择部门"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
        <el-form-item label="顾问" prop="userName">
          <el-input
            v-model="queryParams.userName"
            placeholder="请输入接待顾问姓名"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="来访时间" prop="queryTime">
          <el-date-picker
            v-model="queryParams.queryTime"
            type="datetimerange"
            value-format="timestamp"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            align="right"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>

      <el-table v-loading="loading" :data="visitList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <!-- <el-table-column label="来访记录ID" align="center" prop="id" /> -->
        <el-table-column label="部门" align="center" prop="deptName" />
        <el-table-column label="顾问" align="center" prop="userName" />
        <el-table-column label="来访人员数量" align="center" prop="times" />
      <!-- <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['crm:visit:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['crm:visit:remove']"
          >删除</el-button>
        </template>
      </el-table-column> -->
      </el-table>

      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />

      <!-- 添加或修改客户来访对话框 -->
      <st-dialog
        v-if="open"
        :title="title"
        append-to-body
        @close="cancel"
        @save="submitForm"
      >
        <el-form ref="form" :model="form" :rules="rules" label-width="120px">
          <el-form-item label="客户姓名" prop="customerName">
            <el-input v-model="form.customerName" placeholder="请输入客户姓名" />
          </el-form-item>
          <el-form-item label="接待顾问姓名" prop="userName">
            <el-input v-model="form.userName" placeholder="请输入接待顾问姓名" />
          </el-form-item>
          <el-form-item label="客户手机号" prop="phone">
            <el-input v-model="form.phone" placeholder="请输入客户手机号" />
          </el-form-item>
          <el-form-item label="备注信息" prop="remark">
            <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
          </el-form-item>
        </el-form>
      </st-dialog>
    </div>
  </TablePanel>
</template>

<script>
import { listVisit, getVisit, delVisit, addVisit, updateVisit } from "@/api/crm/visit";

export default {
  name: "Visit",
  data() {
    return {
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
      // 客户来访表格数据
      visitList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        queryTime: [new Date(new Date().getFullYear(), new Date().getMonth(), 1).setHours(0, 0, 0, 0), new Date().getTime()]
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询客户来访列表 */
    getList() {
      this.loading = true;
      listVisit(this.queryParams).then(response => {
        this.visitList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        customerId: null,
        remark: null,
        userId: null,
        createTime: null
      };
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
      this.ids = selection.map(item => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加客户来访";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getVisit(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改客户来访";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateVisit(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addVisit(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除客户来访编号为"' + ids + '"的数据项？').then(function() {
        return delVisit(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download("crm/visit/export", {
        ...this.queryParams
      }, `visit_${new Date().getTime()}.xlsx`);
    }
  }
};
</script>
