<template>
  <div v-loading="loading" class="reContent">
    <st-sections title="跟踪点评">
      <el-form ref="form" :model="form" :rules="rules" class="">

        <el-table v-loading="loading" border :height="tableHeight" :data="remarkList">
          <el-table-column prop="operator" label="操作人"></el-table-column>
          <el-table-column prop="remark" label="备注"></el-table-column>
        </el-table>

      <el-row class="mt10">
        <el-col :span="8">
          <el-tag type="info" style="margin-left: 12px;">已备注</el-tag>
          <el-timeline>
            <el-timeline-item v-for="(item, index) in recordList.remark" :timestamp="item.createTime" placement="top">
              <!-- <el-card> -->
              <div class="flexRowJb">
                <div class="flexCenter">
                  <div class="flexClo">
                    <div style="display: block;">
                      <span>{{ recordList.remark.length - index }}、</span>
                      <span>{{ item.operator }}</span>
                      <span style="margin-left: 12px; word-break: break-all;">{{ item.remark }}</span>
                    </div>
                  </div>
                </div>
              </div>
              <!-- </el-card> -->
            </el-timeline-item>
          </el-timeline></el-col>
        <el-col :span="8">
          <el-tag type="info" style="margin-left: 12px;">已点评</el-tag>
          <el-timeline>
            <el-timeline-item v-for="(item, index) in recordList.comment" :timestamp="item.createTime" placement="top">
              <!-- <el-card> -->
              <div class="flexRowJb">
                <div class="flexCenter">
                  <div class="flexClo">
                    <div style="display: block;">
                      <span>{{ recordList.comment.length - index }}、</span>
                      <span>{{ item.operator }}</span>
                      <span style="margin-left: 12px; word-break: break-all;">{{ item.comment }}</span>
                    </div>
                  </div>
                </div>
              </div>
              <!-- </el-card> -->
            </el-timeline-item>
          </el-timeline></el-col>

      </el-row>
      </el-form>
    </st-sections>
  </div>
</template>

<script>
// import  customer_pool_module from '@/assets/img/customer_pool_module.png'
import { listCustomer_follow, addCustomer_follow, updateCustomer_follow, delCustomer_follow, getFollowDetail } from "@/api/crm/follow";
import { publicCustomer } from "@/api/crm/customer";
export default {
  name: "ContractComment",
  dicts: ["crm_customer_star", "crm_customer_status"],
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
      // customer_pool_module:customer_pool_module,
      open: false,
      tableHeight: undefined,
      remarkList: [],
      queryParams: {},
      form: {},
      updateForm: {},
      recordList: { remark: [], comment: [], ntentPerceptionRemark: [], userName: "" },
      loading: false,
      rules: {}
    };
  },
  mounted() {
    this.getList();
  },
  methods: {
    show() {
      this.getList();
    },
    /** 查询负责人变更记录列表 */
    getList() {
      this.$set(this.queryParams, "customerId", this.customerForm.id);
      this.$set(this.form, "customerId", this.customerForm.id);
      this.$set(this.form, "customerStatus", this.customerForm.status);
      this.$set(this.form, "customerLevel", this.customerForm.level);
      this.loading = true;
      getFollowDetail(this.queryParams.customerId).then(response => {
        this.recordList = response.data;
        this.loading = false;
      });
    },
    /** 修改按钮操作 */
    // handleUpdate(item) {
    //   this.updateForm = {
    //     category: null,
    //     recordTime: null,
    //     nextTime: null,
    //     content:null
    //   };
    //   this.resetForm("updateForm");
    //   this.updateForm =Object.assign(this.updateForm,item) ;
    //   this.open=true;
    // },
    // 取消按钮
    cancel() {
      this.open = false;
    },
    // 表单重置
    reset() {
      this.form = {};
      this.resetForm("form");
    },
    /** 加入公共池按钮 */
    addToPool() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          publicCustomer({ customerIds: [this.customerForm.id] }).then(response => {
            this.$modal.msgSuccess("加入成功");
            this.$emit("close");
          });
        }
      });
    },
    /** 提交按钮 */
    addSubmitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.$set(this.form, "customerId", this.customerForm.id);
          addCustomer_follow(this.form).then(response => {
            this.$modal.msgSuccess("新增成功");
            this.reset();
            this.getList();
          });
        }
      });
    },
    // updateSubmitForm() {
    //   this.$refs["updateForm"].validate(valid => {
    //     if (valid) {
    //       updateCustomer_follow(this.updateForm).then(response => {
    //         this.$modal.msgSuccess("修改成功");
    //         this.open = false;
    //         this.getList();
    //       });
    //     }
    //   });
    // },
    /** 删除按钮操作 */
    handleDelete(item) {
      const recordIds = [item.recordId];
      this.$modal.confirm('是否确认删除记录编号为"' + recordIds + '"的数据项？').then(function() {
        return delCustomer_follow(recordIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    }
  }
};
</script>

<style scoped lang="scss">
.logAddView{
  //width: 100%;
  // height: 350px;
  border: 1px solid #d3d4d6;
  border-radius: 6px;
  box-sizing: border-box;
  padding: 8px;
  margin: 10px;
  position: relative;
  .rightTop{
    position: absolute;
    right: 30px;
    bottom: 10px;
  }
  .el-date-editor{
    margin-left: 10px;
  }
  .el-textarea{
    // margin-top: 10px;
    // min-width: 300px;
  }
  .el-form {
    width: 90%;
  }
  ::v-deep .el-textarea__inner{
    // border: 0 !important;
    // resize: none !important;
  }
  .select {
    // text-align: center;
    margin-bottom: 10px;
    align-items: center;
    .item {
      margin: 10px 10px 10px 0;
    }
    .input_left {
      display: flex;
      align-items: center;
      span {
        width: 130px;
      }
    }
    span {
      margin-left: 20px;
      font-size: 14px;
    }
  }
  .remark {
    margin: 20px;
  }
}

.reContent{
  height: calc(100vh - 273px);
  overflow: auto;
}
.el-timeline{
  padding-left: 0px;
  margin: 10px;
}

.flexRowJb{
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.flexClo{
  display: flex;
  flex-direction: column;
}

.flexCenter {
  display: flex;
  align-items: center;
}
</style>
