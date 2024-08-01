<template>
  <!-- todo信息排版 -->
  <drawer-view
    :body-style="{ height: '100%'}"
    width="80%"
    @close="close"
    @afterEnter="afterEnter"
  >
    <flexbox
      v-loading="loading"
      direction="column"
      align="stretch"
      class="st-drawer"
    >
      <flexbox class="st-drawer__header">
        <img :src="customer_active" class="header-mark">
        <div class="title">{{ params.customerName }}</div>
        <div>
          <!-- <el-button type="primary" plain @click="updateCustomer">修 改</el-button> -->
        </div>
      </flexbox>
      <div class="st-drawer__body">
        <st-sections title="基本信息">
          <el-descriptions :column="3" border style="margin-top: 10px;">
            <el-descriptions-item label="客户名称:">{{ params.name }}</el-descriptions-item>
            <el-descriptions-item label="手机电话:">{{ params.phone }}</el-descriptions-item>
            <el-descriptions-item label="年龄:">{{ params.age }}</el-descriptions-item>
            <el-descriptions-item label="性别:">{{ params.sex?getSex(params.sex):'' }}</el-descriptions-item>
            <el-descriptions-item label="户籍:">{{ params.region }}</el-descriptions-item>
            <el-descriptions-item label="客户星级:">
              <dict-tag :options="dict.type.crm_customer_star" :value="params.level" />
            </el-descriptions-item>
            <!-- <el-descriptions-item label="客户分级:">
              <el-tag size="small">{{params.gradeDisp}}</el-tag>
            </el-descriptions-item> -->
          </el-descriptions>
        </st-sections>
        <st-sections title="关联信息">
          <el-tabs v-model="activeName" type="border-card" style="margin-top: 10px;" @tab-click="handleClick">
            <el-tab-pane label="详细信息" name="detail">
              <detail :customer-form="params" />
            </el-tab-pane>
            <el-tab-pane label="客户跟踪" name="record">
              <record ref="record" :customer-form="params" @close="close" />
            </el-tab-pane>
            <el-tab-pane label="分配记录" name="contacts">
              <contacts ref="contactsRef" :customer-form="params" />
            </el-tab-pane>
            <el-tab-pane label="跟踪点评" name="contractComment">
              <contractComment ref="contractComment" :customer-form="params" />
            </el-tab-pane>
            <!-- <el-tab-pane label="商机" name="business">
              <business ref="businessRef" :customerForm="params"></business>
            </el-tab-pane>
            <el-tab-pane label="合同" name="contract">
              <contract ref="contractRef" :customerForm="params"></contract>
            </el-tab-pane>
            <el-tab-pane label="回款计划" name="plan">
              <plan ref="planRef" :customerForm="params"></plan>
            </el-tab-pane>
            <el-tab-pane label="回款单" name="receivables">
              <receivables ref="receiveablesRef" :customerForm="params"></receivables>
            </el-tab-pane>
            <el-tab-pane label="操作记录" name="operate">
              <operate ref="operateRef" :customerForm="params"></operate>
            </el-tab-pane> -->
          </el-tabs>
        </st-sections>
      </div>
    </flexbox>
    <add-or-update ref="addOrUpdate" @updateParams="updateParams" />
  </drawer-view>
</template>

<script>
import { SEX } from "@/maps/dicts";
import DrawerView from "@/components/StDrawer/DrawerView.vue";
import record from "./record";
import flexbox from "@/components/Flexbox/Flexbox.vue";
import customer_active from "@/assets/img/address_logo.png";
import detail from "./detail.vue";
// import business from "./business/main.vue";
// import contract from './contract/main.vue'
// import plan from './plan/main.vue'
// import receivables from './receivables/main.vue'
import contacts from "./contacts.vue";
import contractComment from "./contactComment.vue";
// import operate from './operate.vue'
import addOrUpdate from "@/views/crm/customer/addOrUpdate.vue";
export default {
  // 创建页面
  name: "XrCreate",
  dicts: ["crm_customer_star", "crm_loan_type", "crm_customer_status", "sys_user_sex", "crm_has_not"],
  components: {
    addOrUpdate,
    // business,
    contacts,
    record,
    DrawerView,
    flexbox,
    detail,
    contractComment
    // plan,
    // receivables,
    // contract,
    // operate
  },
  props: {
    title: { type: String,
      default: ""
    },
    loading: Boolean,
    appendToBody: {
      type: Boolean,
      default: true
    },
    showCancel: {
      type: Boolean,
      default: true
    },
    showConfirm: {
      type: Boolean,
      default: true
    },
    confirmDisabled: {
      type: Boolean,
      default: false
    },
    confirmButtonText: {
      type: String,
      default: "保存"
    },
    params: {
      type: Object,
      default: function() {
        return {};
      }
    }
  },
  data() {
    return {
      customer_active: customer_active,
      activeName: "record"
    };
  },
  computed: {},
  watch: {},
  created() {
  },
  mounted() {
    if (this.appendToBody) {
      document.body.appendChild(this.$el);
    }
  },

  destroyed() {
    if (this.appendToBody) {
      if (this.$el && this.$el.parentNode) {
        this.$el.parentNode.removeChild(this.$el);
      }
    }
  },
  methods: {
    /** 性别的展示 */
    getSex(sex) {
      return SEX[sex];
    },
    show(param) {
      // this.params=Object.assign('',param);
    },

    handleClick(tab, event) {
      switch (tab.index) {
        case "0":
          this.$refs.record.show();
          break;
        case "2":
          this.$refs.contactsRef.getList();
          break;
        case "1":
          this.$refs.contractComment.getList();
          break;
        case "3":
          this.$refs.businessRef.showTabList();
          break;
        case "4":
          this.$refs.contractRef.showTabList();
          break;
        case "5":
          this.$refs.planRef.showTabList();
          break;
        case "6":
          this.$refs.receiveablesRef.showTabList();
          break;
        case "7":
          this.$refs.operateRef.getList();
          break;
      }
      console.log(tab, event);
    },

    afterEnter() {
      this.$emit("afterEnter");
    },

    updateCustomer() {
      this.$refs["addOrUpdate"].show(this.params);
    },

    updateParams(data) {
      this.params = data;
    },

    save() {
      this.$emit("save");
    },

    close() {
      this.$emit("close");
    }
  }
};
</script>

<style lang="scss" scoped>
.st-drawer {
  position: relative;
  height: 100%;

  &__header {
    height: 40px;
    margin-bottom: 10px;
    padding: 0 10px;
    flex-shrink: 0;
    border-bottom: 1px solid #d3dce6;
    .title {
      flex:1;
      font-size:17px;
      color:#333;
      font-weight: bold;
      margin-left: 10px;
    }
    .close {
      display: block;
      font-size: 24px;
      color: #909399;
      margin-right: -10px;
      padding: 10px;
      cursor: pointer;
    }
    .close:hover {
      color: #909399;
    }
  }

  &__body {
    position: relative;
    overflow-x: hidden;
    overflow-y: auto;
    flex: 1;
  }

  &__footer {
    position: relative;
    text-align: center;
    padding: 10px 20px 0;
    border-top: 1px solid #d3dce6;
  }

  .header-mark{
    //border-left-color: #2362FB;
    //border-left-width: 6px;
    //border-left-style: solid;
    //border-radius: 0px;
    height: 30px;
    width: 30px;
    //padding-right: 9px;
  }
}
</style>
