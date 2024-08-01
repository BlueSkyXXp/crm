<template>
  <dialog-view
    :body-style="{ height: '100%'}"
    :padding="padding"
    :height="height"
    :width="width"
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
        <div class="header-mark" />
        <div v-if="!$slots.title" class="title">{{ title }}</div>
        <slot name="title" />
        <i
          class="el-icon-close close"
          @click="close"
        />
      </flexbox>
      <div class="st-drawer__body">
        <slot />
      </div>
      <div class="st-drawer__footer">
        <slot name="footer-left" />
        <el-button
          v-if="showCancel"
          icon="el-icon-close"
          @click.native="close"
        >取消</el-button>
        <slot name="footer" />
        <el-button
          v-if="showConfirm"
          :disabled="confirmDisabled"
          type="primary"
          icon="el-icon-refresh"
          @click.native="debouncedSaveField(false)"
        >{{ confirmButtonText }}</el-button>
      </div>
    </flexbox>
  </dialog-view>
</template>

<script>
import DialogView from "./DialogView.vue";
import { debounce } from "throttle-debounce";
import flexbox from "@/components/Flexbox/Flexbox.vue";
export default {
  // 创建页面
  name: "XrCreate",
  components: {
    DialogView,
    flexbox
  },
  props: {
    title: String,
    loading: Boolean,
    /** 展示内容的宽 */
    width: {
      type: String,
      default: "900px"
    },
    /** 展示内容的宽 */
    height: {
      type: String,
      default: "500px"
    },
    /** 展示内容的上下padding */
    padding: {
      type: String,
      default: "40px"
    },
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
    }
  },
  data() {
    return {};
  },
  computed: {
  },
  watch: {},
  created() {
    this.debouncedSaveField = debounce(300, this.save);
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
    afterEnter() {
      this.$emit("afterEnter");
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
    //margin-bottom: 20px;
    padding: 0 10px;
    flex-shrink: 0;
    border-bottom: 1px solid #d3dce6;
    .title {
      flex:1;
      font-size:17px;
      color:#333;
      font-weight: bold;
    }
    .close {
      display: block;
      font-size: 19px;
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
    padding:10px 0;
  }

  &__footer {
    position: relative;
    text-align: right;
    padding: 10px 20px 0;
    border-top: 1px solid #d3dce6;
  }

  .header-mark{
    border-left-color: #2362FB;
    border-left-width: 6px;
    border-left-style: solid;
    border-radius: 0px;
    height: 17px;
    padding-right: 9px;
  }
}
</style>
