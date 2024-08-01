<template>
  <div class="page-container">
    <div class="panel-head">
      <div class="card-panel" @click="handleSetLineChartData('newVisitis')">
        <div class="card-panel-icon-wrapper icon-people">
          <img src="@/assets/images/left.488a0236.png" class="card-panel-icon">
          下午好 系统管理员
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            <el-cascader
              v-model="value"
              :options="options"
            />

            <el-date-picker
              v-model="value"
              style="margin-left: 10px;"
              type="daterange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
            />
          </div>
        </div>
      </div>
    </div>
    <st-sections title="销售简报">
      <el-row :gutter="20" class="panel-group">
        <el-col :span="6" class="card-panel-col">
          <div class="card-panel" @click="handleSetLineChartData('newVisitis')">
            <div class="card-panel-icon-wrapper icon-people">
              <svg-icon icon-class="peoples" class-name="card-panel-icon" />
            </div>
            <div class="card-panel-description">
              <div class="card-panel-text">
                访客
              </div>
              <count-to :start-val="0" :end-val="102400" :duration="2600" class="card-panel-num" />
            </div>
          </div>
        </el-col>
        <el-col :span="6" class="card-panel-col">
          <div class="card-panel" @click="handleSetLineChartData('messages')">
            <div class="card-panel-icon-wrapper icon-message">
              <svg-icon icon-class="message" class-name="card-panel-icon" />
            </div>
            <div class="card-panel-description">
              <div class="card-panel-text">
                消息
              </div>
              <count-to :start-val="0" :end-val="81212" :duration="3000" class="card-panel-num" />
            </div>
          </div>
        </el-col>
        <el-col :span="6" class="card-panel-col">
          <div class="card-panel" @click="handleSetLineChartData('purchases')">
            <div class="card-panel-icon-wrapper icon-money">
              <svg-icon icon-class="money" class-name="card-panel-icon" />
            </div>
            <div class="card-panel-description">
              <div class="card-panel-text">
                金额
              </div>
              <count-to :start-val="0" :end-val="9280" :duration="3200" class="card-panel-num" />
            </div>
          </div>
        </el-col>
        <el-col :span="6" class="card-panel-col">
          <div class="card-panel" @click="handleSetLineChartData('shoppings')">
            <div class="card-panel-icon-wrapper icon-shopping">
              <svg-icon icon-class="shopping" class-name="card-panel-icon" />
            </div>
            <div class="card-panel-description">
              <div class="card-panel-text">
                订单
              </div>
              <count-to :start-val="0" :end-val="13600" :duration="3600" class="card-panel-num" />
            </div>
          </div>
        </el-col>

        <el-col :span="6" class="card-panel-col">
          <div class="card-panel" @click="handleSetLineChartData('newVisitis')">
            <div class="card-panel-icon-wrapper icon-people">
              <svg-icon icon-class="peoples" class-name="card-panel-icon" />
            </div>
            <div class="card-panel-description">
              <div class="card-panel-text">
                访客
              </div>
              <count-to :start-val="0" :end-val="102400" :duration="2600" class="card-panel-num" />
            </div>
          </div>
        </el-col>
        <el-col :span="6" class="card-panel-col">
          <div class="card-panel" @click="handleSetLineChartData('messages')">
            <div class="card-panel-icon-wrapper icon-message">
              <svg-icon icon-class="message" class-name="card-panel-icon" />
            </div>
            <div class="card-panel-description">
              <div class="card-panel-text">
                消息
              </div>
              <count-to :start-val="0" :end-val="81212" :duration="3000" class="card-panel-num" />
            </div>
          </div>
        </el-col>
        <el-col :span="6" class="card-panel-col">
          <div class="card-panel" @click="handleSetLineChartData('purchases')">
            <div class="card-panel-icon-wrapper icon-money">
              <svg-icon icon-class="money" class-name="card-panel-icon" />
            </div>
            <div class="card-panel-description">
              <div class="card-panel-text">
                金额
              </div>
              <count-to :start-val="0" :end-val="9280" :duration="3200" class="card-panel-num" />
            </div>
          </div>
        </el-col>
        <el-col :span="6" class="card-panel-col">
          <div class="card-panel" @click="handleSetLineChartData('shoppings')">
            <div class="card-panel-icon-wrapper icon-shopping">
              <svg-icon icon-class="shopping" class-name="card-panel-icon" />
            </div>
            <div class="card-panel-description">
              <div class="card-panel-text">
                订单
              </div>
              <count-to :start-val="0" :end-val="13600" :duration="3600" class="card-panel-num" />
            </div>
          </div>
        </el-col>
      </el-row>
    </st-sections>

    <el-row :gutter="20" class="panel-group">
      <el-col :span="12">
        <div class="card-panel" style="height:300px;" @click="handleSetLineChartData('newVisitis')">
          <st-sections title="销售简报">
            <raddar-chart />
          </st-sections>
        </div>
      </el-col>

      <el-col :span="12">
        <div class="card-panel" style="height:300px;" @click="handleSetLineChartData('newVisitis')">
          <st-sections title="销售简报">
            <pie-chart />
          </st-sections>
        </div>
      </el-col>

    </el-row>

    <el-row :gutter="20" class="panel-group">
      <el-col :span="12">
        <div class="card-panel" style="height:300px;" @click="handleSetLineChartData('newVisitis')">
          <st-sections title="销售简报">
            <bar-chart />
          </st-sections>
        </div>
      </el-col>

      <el-col :span="12">
        <div class="card-panel" style="height:300px;" @click="handleSetLineChartData('newVisitis')">
          <st-sections title="销售简报">
            <LineChart :chart-data="lineChartData" />
          </st-sections>
        </div>
      </el-col>

    </el-row>
  </div>
</template>

<script>
import CountTo from "vue-count-to";
import LineChart from "./LineChart";
import RaddarChart from "./RaddarChart";
import PieChart from "./PieChart";
import BarChart from "./BarChart";
const lineChartData = {
  newVisitis: {
    expectedData: [100, 120, 161, 134, 105, 160, 165],
    actualData: [120, 82, 91, 154, 162, 140, 145]
  },
  messages: {
    expectedData: [200, 192, 120, 144, 160, 130, 140],
    actualData: [180, 160, 151, 106, 145, 150, 130]
  },
  purchases: {
    expectedData: [80, 100, 121, 104, 105, 90, 100],
    actualData: [120, 90, 100, 138, 142, 130, 130]
  },
  shoppings: {
    expectedData: [130, 140, 141, 142, 145, 150, 160],
    actualData: [120, 82, 91, 154, 162, 140, 130]
  }
};
export default {
  components: {
    CountTo,
    LineChart,
    RaddarChart,
    PieChart,
    BarChart
  },
  data() {
    return {
      lineChartData: lineChartData.newVisitis,
      value: [],
      options: [{
        value: "zhinan",
        label: "指南",
        children: [{
          value: "shejiyuanze",
          label: "设计原则",
          children: [{
            value: "yizhi",
            label: "一致"
          }, {
            value: "fankui",
            label: "反馈"
          }, {
            value: "xiaolv",
            label: "效率"
          }, {
            value: "kekong",
            label: "可控"
          }]
        }, {
          value: "daohang",
          label: "导航",
          children: [{
            value: "cexiangdaohang",
            label: "侧向导航"
          }, {
            value: "dingbudaohang",
            label: "顶部导航"
          }]
        }]
      }, {
        value: "zujian",
        label: "组件",
        children: [{
          value: "basic",
          label: "Basic",
          children: [{
            value: "layout",
            label: "Layout 布局"
          }, {
            value: "color",
            label: "Color 色彩"
          }, {
            value: "typography",
            label: "Typography 字体"
          }, {
            value: "icon",
            label: "Icon 图标"
          }, {
            value: "button",
            label: "Button 按钮"
          }]
        }, {
          value: "form",
          label: "Form",
          children: [{
            value: "radio",
            label: "Radio 单选框"
          }, {
            value: "checkbox",
            label: "Checkbox 多选框"
          }, {
            value: "input",
            label: "Input 输入框"
          }, {
            value: "input-number",
            label: "InputNumber 计数器"
          }, {
            value: "select",
            label: "Select 选择器"
          }, {
            value: "cascader",
            label: "Cascader 级联选择器"
          }, {
            value: "switch",
            label: "Switch 开关"
          }, {
            value: "slider",
            label: "Slider 滑块"
          }, {
            value: "time-picker",
            label: "TimePicker 时间选择器"
          }, {
            value: "date-picker",
            label: "DatePicker 日期选择器"
          }, {
            value: "datetime-picker",
            label: "DateTimePicker 日期时间选择器"
          }, {
            value: "upload",
            label: "Upload 上传"
          }, {
            value: "rate",
            label: "Rate 评分"
          }, {
            value: "form",
            label: "Form 表单"
          }]
        }, {
          value: "data",
          label: "Data",
          children: [{
            value: "table",
            label: "Table 表格"
          }, {
            value: "tag",
            label: "Tag 标签"
          }, {
            value: "progress",
            label: "Progress 进度条"
          }, {
            value: "tree",
            label: "Tree 树形控件"
          }, {
            value: "pagination",
            label: "Pagination 分页"
          }, {
            value: "badge",
            label: "Badge 标记"
          }]
        }, {
          value: "notice",
          label: "Notice",
          children: [{
            value: "alert",
            label: "Alert 警告"
          }, {
            value: "loading",
            label: "Loading 加载"
          }, {
            value: "message",
            label: "Message 消息提示"
          }, {
            value: "message-box",
            label: "MessageBox 弹框"
          }, {
            value: "notification",
            label: "Notification 通知"
          }]
        }, {
          value: "navigation",
          label: "Navigation",
          children: [{
            value: "menu",
            label: "NavMenu 导航菜单"
          }, {
            value: "tabs",
            label: "Tabs 标签页"
          }, {
            value: "breadcrumb",
            label: "Breadcrumb 面包屑"
          }, {
            value: "dropdown",
            label: "Dropdown 下拉菜单"
          }, {
            value: "steps",
            label: "Steps 步骤条"
          }]
        }, {
          value: "others",
          label: "Others",
          children: [{
            value: "dialog",
            label: "Dialog 对话框"
          }, {
            value: "tooltip",
            label: "Tooltip 文字提示"
          }, {
            value: "popover",
            label: "Popover 弹出框"
          }, {
            value: "card",
            label: "Card 卡片"
          }, {
            value: "carousel",
            label: "Carousel 走马灯"
          }, {
            value: "collapse",
            label: "Collapse 折叠面板"
          }]
        }]
      }, {
        value: "ziyuan",
        label: "资源",
        children: [{
          value: "axure",
          label: "Axure Components"
        }, {
          value: "sketch",
          label: "Sketch Templates"
        }, {
          value: "jiaohu",
          label: "组件交互文档"
        }]
      }]
    };
  },
  methods: {
    handleSetLineChartData(type) {
      this.$emit("handleSetLineChartData", type);
    }
  }
};
</script>

<style lang="scss" scoped>
.panel-group {
  margin-top: 18px;
  margin-left: 0px !important;
  margin-right: 0px !important;
  .card-panel-col {
    margin-bottom: 32px;
  }

  .card-panel {
    height: 85px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    background: #fff;
    border-radius:6px;
    //box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
    border-color: rgba(0, 0, 0, .05);

    &:hover {
      box-shadow: 0 0 16px 0 rgba(0,0,0,.25);
      .card-panel-icon-wrapper {
        color: #fff;
      }

      .icon-people {
        background: #40c9c6;
      }

      .icon-message {
        background: #36a3f7;
      }

      .icon-money {
        background: #f4516c;
      }

      .icon-shopping {
        background: #34bfa3
      }
    }

    .icon-people {
      color: #40c9c6;
    }

    .icon-message {
      color: #36a3f7;
    }

    .icon-money {
      color: #f4516c;
    }

    .icon-shopping {
      color: #34bfa3
    }

    .card-panel-icon-wrapper {
      float: left;
      margin: 14px 0 0 14px;
      padding: 5px;
      transition: all 0.38s ease-out;
      border-radius: 6px;
    }

    .card-panel-icon {
      float: left;
      font-size: 48px;
    }

    .card-panel-description {
      float: right;
      font-weight: bold;
      margin: 16px;
      margin-left: 0px;

      .card-panel-text {
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 16px;
        margin-bottom: 12px;
      }

      .card-panel-num {
        font-size: 20px;
      }
    }
  }
}
.panel-head {
  background-color: #f0f2f5;
  margin: 0px 10px;
  .card-panel-col {
    margin-bottom: 32px;
  }

  .card-panel {
    height: 85px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    background: #fff;
    border-radius:6px;
    border-color: rgba(0, 0, 0, .05);
    .card-panel-icon-wrapper {
      float: left;
      color: #333;
      font-weight: 600;
      font-size: 16px;
      line-height: 85px;
      margin-left: 42px;
    }

    .card-panel-icon {
      float: left;
      font-size: 48px;
      height: 90px;
    }

    .card-panel-description {
      float: right;
      font-weight: bold;
      margin: 25px;
      margin-left: 0px;

      .card-panel-text {
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 16px;
      }

      .card-panel-num {
        font-size: 20px;
      }
    }
  }
}

.section{
  background-color:unset;
  margin-top:10px;
}
::v-deep .section-title{
  font-size: 16px !important;

}
::v-deep .section-mark{
  height:16px !important;
  border-left-width: 6px !important;
}

@media (max-width:550px) {
  .card-panel-description {
    display: none;
  }

  .card-panel-icon-wrapper {
    float: none !important;
    width: 100%;
    height: 100%;
    margin: 0 !important;

    .svg-icon {
      display: block;
      margin: 14px auto !important;
      float: none !important;
    }
  }
}

.page-container {
  /*padding: 10px;
  width: 100%;*/
  background-color: rgb(240, 242, 245);
  border: 1px solid #eaeffc;
  margin: 10px;
  padding: 10px;
  height: calc(100vh - 104px);
  overflow: auto;
  box-shadow: 0px 0px 12px 0px rgba(188, 188, 188, 0.36);
  .title {
    font-size: 16px;
    font-weight: 700;
    border-bottom: 1px solid #e6ebf5;
    box-sizing: border-box;
    display: flex;
    padding: 10px 5px;
    align-items: center;
    .btn-area {
      -webkit-box-flex: 1;
      flex: 1;
      text-align: right;
      margin-right: 0px;
    }
    .title-icon {
      width: 6px;
      height: 20px;
      background-color: #409EFF;
      background-size: 100% 100%;
      margin: 1px 7px 0px 7px;
    }
  }
  .content-container {
    /*background: #f2f2f2;
    margin-top: 5px;*/
    padding-left: 10px;
    padding-right: 10px;

  }
}
</style>
