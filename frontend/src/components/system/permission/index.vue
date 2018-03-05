<template>
  <div class="wrapper">
    <Breadcrumb class="breadcrumb">
        <BreadcrumbItem>{{$t('menu.home')}}</BreadcrumbItem>
        <BreadcrumbItem>{{$t('menu.system')}}</BreadcrumbItem>
        <BreadcrumbItem>{{$t('menu.system.permission')}}</BreadcrumbItem>
    </Breadcrumb>
    <div class="toolbar">
      <Button type="primary" icon="plus" size="small">{{$t('common.func.add')}}</Button>
    </div>
    <div class="list">
      <zk-table
        :columns="columns"
        :data="data"
        :selection-type="false"
        :tree-type="true"
        :expand-type="false"
        :border="true"
        row-class-name="line">
      <template slot="ops" slot-scope="scope">
        <Button class="operations" type="primary" size="small" @click="edit(scope.row)">{{$t('common.func.edit')}}</Button>
        <Button class="operations" type="success" size="small">{{$t('permission.func.move.up')}}</Button>
        <Button class="operations" type="success" size="small">{{$t('permission.func.move.down')}}</Button>
        <Button class="operations" type="error" size="small">{{$t('common.func.remove')}}</Button>
      </template>
      </zk-table>
    </div>
  </div>
</template>
<style>
@import url('../../../../static/css/content.css');

.line {
  padding: 3px 5px!important;
  height: 25px!important;
}

.zk-table__cell-inner {
  padding: 5px 10px!important;
}
.zk-checkbox-wrapper {
  padding-left: 8px;
}
.zk-icon-plus-square-o:before {
  content: '\E633'!important;
}
.zk-icon-minus-square-o:before {
  content: '\E633'!important;
  display:inline-block;
  transform: rotate(90deg);
}
</style>

<script>
import PermissionService from './permission.service'
export default {
  name: 'PermissionIndex',
  created: function () {
    this.service = new PermissionService()
    this.service.getData()
      .then(res => {
        this.data = res
      })
      .catch(err => {
        console.log(err)
      })
  },
  data () {
    return {
      columns: [
        {label: this.$i18n.t('permission.column.name'), prop: 'name', width: '25%'},
        {label: this.$i18n.t('permission.column.code'), prop: 'code', width: '20%'},
        {label: this.$i18n.t('permission.column.category'), prop: 'category', width: '10%'},
        {label: this.$i18n.t('common.func.operations'), type: 'template', width: '45%', template: 'ops'}
      ],
      data: []
    }
  },
  methods: {
    edit: function (inst) {
      console.log(inst)
    }
  }
}
</script>