<template>
  <st-distpicker
    :province="value[0]"
    :city="value[1]"
    :area="value[2]"
    @province="selectProvince"
    @city="selectCity"
    @area="selectArea"
  />
</template>
<script type="text/javascript">
import StDistpicker from "@/components/StDistpicker";
import { getBaiduMap } from "@/utils";

export default {
  name: "StAddress", // 位置
  components: {
    StDistpicker
  },
  props: {
    value: {
      type: Object,
      default: () => {
        return {};
      }
    },
    /** 索引值 用于更新数据 */
    index: Number,
    /** 包含数据源 */
    item: Object
  },
  data() {
    return {
      map: null,
      /** 搜索地图输入框 */
      searchInput: "",
      searchCopyInput: "", // 避免修改
      /** 完整地址输入框 */
      detailAddress: "",
      pointAddress: null, // 经纬度点
      /** 区域选择 */
      addressSelect: {
        province: "",
        city: "",
        area: ""
      },
      /** 防止联动情况  */
      canExecute: true
    };
  },
  computed: {

  },
  watch: {
    pointAddress: function(newValue) {
      this.valueChange();
    },
    detailAddress: function(newValue) {
      this.valueChange();
    }
  },
  mounted() {

  },
  methods: {
    initInfo(val) {
      var address = {};
      for (let index = 0; index < val.length; index++) {
        index === 0 ? (address["province"] = val[0]) : "";
        index === 1 ? (address["city"] = val[1]) : "";
        index === 2 ? (address["area"] = val[2]) : "";
      }
      this.addressSelect = address;
      console.log(this.addressSelect);
    },
    querySearchAsync(queryString, cb) {
      if (queryString) {
        var options = {
          onSearchComplete: function(results) {
            if (local.getStatus() == BMAP_STATUS_SUCCESS) {
              var address = [];
              for (var i = 0; i < results.getCurrentNumPois(); i++) {
                address.push(results.getPoi(i));
              }
              cb(address);
            } else {
              cb([]);
            }
          },
          pageCapacity: 20
        };
        var local = new BMap.LocalSearch(this.map, options);
        local.search(queryString);
      } else {
        cb([]);
      }
    },
    /** 搜索结果选择 */
    handleSelect(item) {
      this.searchInput = item.address + item.title;
      this.searchCopyInput = this.searchInput; // 只能通过这种方式修改

      this.detailAddress = this.searchInput;
      this.addMarkerLabel(item.point);
      this.pointAddress = item.point;
      this.mapSelectArea(item);
    },
    /** Input 失去焦点  searchInput 只能通过选择更改*/
    inputBlur() {
      if (this.searchCopyInput !== this.searchInput) {
        this.searchInput = this.searchCopyInput;
      }
    },
    inputFocus() {
      this.searchCopyInput = this.searchInput;
    },
    // 创建标注
    addMarkerLabel(point) {
      this.map.clearOverlays();
      this.map.centerAndZoom(point, 14);
      this.map.addOverlay(new BMap.Marker(point));
    },
    /** 区域选择 */
    selectProvince(value) {
      this.addressSelect.province = value.value;
      this.valueChange();
    },
    selectCity(value) {
      this.addressSelect.city = value.value;
      this.valueChange();
    },
    selectArea(value) {
      this.addressSelect.area = value.value;
      this.valueChange();
    },
    /** 地图选择区域 */
    mapSelectArea(data) {
      if (this.canExecute) {
        this.canExecute = false;
        // this.addressSelect.province = data.province ? data.province : "";
        // this.addressSelect.city = data.city ? data.city : "";
        /** 因为poi里面不包含区域 所以需要逆地址解析 */
        var myGeo = new BMap.Geocoder();
        // 根据坐标得到地址描述
        var self = this;
        myGeo.getLocation(
          new BMap.Point(data.point.lng, data.point.lat),
          function(result) {
            if (result) {
              // 获取经纬度点
              self.pointAddress = result.point;
              self.addressSelect.province = result.addressComponents.province
                ? result.addressComponents.province
                : "";
              self.addressSelect.city = result.addressComponents.city
                ? result.addressComponents.city
                : "";
              self.addressSelect.area = result.addressComponents.district
                ? result.addressComponents.district
                : "";
            }
          }
        );

        setTimeout(() => {
          self.canExecute = true;
        }, 500);
      }
    },
    // 值更新的回调
    valueChange() {
      this.$emit("value-change", {
        index: this.index,
        value: {
          address: [
            this.addressSelect.province,
            this.addressSelect.city,
            this.addressSelect.area
          ],
          location: this.searchInput,
          detailAddress: this.detailAddress,
          lat: this.pointAddress ? this.pointAddress.lat : "",
          lng: this.pointAddress ? this.pointAddress.lng : ""
        }
      });
    }
  }
};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.map {
  height: 150px;
  width: 100%;
  overflow: hidden;
  margin-top: 5px;
}

.area-title {
  font-size: 12px;
  color: #aaa;
  padding-left: 10px;
}
</style>
