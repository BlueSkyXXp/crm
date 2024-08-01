<template>
  <TablePanel title="团队管理">
    <div slot="btm">
      <el-button
        v-hasPermi="['crm:team_manage:allow_receive']"
        type="primary"
        plain
        @click="handleAllowReceive(1)"
      >一键开启接收</el-button>
      <el-button
        v-hasPermi="['crm:team_manage:allow_receive']"
        type="danger"
        plain
        @click="handleAllowReceive(0)"
      >一键关闭接收</el-button>
      <!-- <el-button
              type="warning"
              plain
              icon="el-icon-download"
              @click="handleExport"
              v-hasPermi="['crm:manage:export']"
          >导出</el-button> -->
      <right-toolbar :show-search.sync="showSearch" @queryTable="getList" />
    </div>
    <div slot="content">

      <el-table v-loading="loading" :data="manageList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <!-- <el-table-column label="自增ID" align="center" prop="id" /> -->
        <el-table-column label="姓名" align="center" prop="userName" />
        <el-table-column label="部门" align="center" prop="deptName" />
        <el-table-column label="日分配数据数" align="center" prop="planDataNum" />
        <el-table-column label="实际日分配数据数" align="center" prop="receiveDataNum" />
        <el-table-column label="是否接收新数据" align="center" prop="receiveNewData" :formatter="getStatus" />
        <!-- <el-table-column label="备注信息" align="center" prop="remark" /> -->
        <el-table-column label="操作者" align="center" prop="operatorName" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              v-hasPermi="['crm:team_manage:edit']"
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
            >修改</el-button>
          <!-- <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['crm:manage:remove']"
          >删除</el-button> -->
          </template>
        </el-table-column>
      </el-table>

      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />

      <!-- 添加或修改团队管理对话框 -->
      <st-dialog
        v-if="open"
        :title="title"
        append-to-body
        @close="cancel"
        @save="submitForm"
      >
        <el-form ref="form" :model="form" :rules="rules" label-width="180px">
          <!-- <el-form-item label="跟进人(用户ID)" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入跟进人(用户ID)" />
        </el-form-item> -->
          <el-form-item label="日分配数据数" prop="planDataNum">
            <el-input v-model="form.planDataNum" placeholder="请输入日分配数据数" />
          </el-form-item>
        <!-- <el-form-item label="实际日分配数据数" prop="receiveDataNum">
          <el-input v-model="form.receiveDataNum" placeholder="请输入实际日分配数据数" />
        </el-form-item>
        <el-form-item label="是否接收新数据: 接收,不接收" prop="receiveNewData">
          <el-input v-model="form.receiveNewData" placeholder="请输入是否接收新数据: 接收,不接收" />
        </el-form-item>
        <el-form-item label="是否接收公共池数据: 接收,不接收" prop="receivePublicData">
          <el-input v-model="form.receivePublicData" placeholder="请输入是否接收公共池数据: 接收,不接收" />
        </el-form-item>
        <el-form-item label="是否允许登录: 1, 0" prop="login">
          <el-input v-model="form.login" placeholder="请输入是否允许登录: 1, 0" />
        </el-form-item>
        <el-form-item label="备注信息" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="操作者(用户ID)" prop="operatorId">
          <el-input v-model="form.operatorId" placeholder="请输入操作者(用户ID)" />
        </el-form-item> -->
        </el-form>
      </st-dialog>
    </div>
  </TablePanel>
</template>

<script>
import { listManage, getManage, delManage, addManage, updateManage, allowReceive } from "@/api/crm/manage";
import { RECIVE_NOT } from "@/maps/dicts";

export default {
  name: "Manage",
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
      // 团队管理表格数据
      manageList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        planDataNum: null,
        receiveDataNum: null,
        receiveNewData: null,
        receivePublicData: null,
        login: null,
        operatorId: null
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
    /** 是否接收的展示 */
    getStatus(row, column) {
      return RECIVE_NOT[row.receiveNewData];
    },
    /** 查询团队管理列表 */
    getList() {
      this.loading = true;
      listManage(this.queryParams).then(response => {
        this.manageList = response.rows;
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
      this.ids = selection.map(item => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加团队管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getManage(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateManage(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addManage(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除ID为"' + ids + '"的数据项？').then(function() {
        return delManage(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download("crm/manage/export", {
        ...this.queryParams
      }, `manage_${new Date().getTime()}.xlsx`);
    },
    /** 一键🉑接收关闭 */
    handleAllowReceive(allowFlag) {
      allowReceive({ allowReceive: allowFlag, ids: this.ids }).then(response => {
        this.$modal.msgSuccess("操作成功");
        this.getList();
      });
    }
  }
};
</script>
