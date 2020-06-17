<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="父级权限id" prop="pid">
      <el-input v-model="dataForm.pid" placeholder="父级权限id"></el-input>
    </el-form-item>
    <el-form-item label="名称" prop="name">
      <el-input v-model="dataForm.name" placeholder="名称"></el-input>
    </el-form-item>
    <el-form-item label="权限值" prop="value">
      <el-input v-model="dataForm.value" placeholder="权限值"></el-input>
    </el-form-item>
    <el-form-item label="图标" prop="icon">
      <el-input v-model="dataForm.icon" placeholder="图标"></el-input>
    </el-form-item>
    <el-form-item label="权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）" prop="type">
      <el-input v-model="dataForm.type" placeholder="权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）"></el-input>
    </el-form-item>
    <el-form-item label="前端资源路径" prop="uri">
      <el-input v-model="dataForm.uri" placeholder="前端资源路径"></el-input>
    </el-form-item>
    <el-form-item label="启用状态；0->禁用；1->启用" prop="status">
      <el-input v-model="dataForm.status" placeholder="启用状态；0->禁用；1->启用"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="排序" prop="sort">
      <el-input v-model="dataForm.sort" placeholder="排序"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          pid: '',
          name: '',
          value: '',
          icon: '',
          type: '',
          uri: '',
          status: '',
          createTime: '',
          sort: ''
        },
        dataRule: {
          pid: [
            { required: true, message: '父级权限id不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '名称不能为空', trigger: 'blur' }
          ],
          value: [
            { required: true, message: '权限值不能为空', trigger: 'blur' }
          ],
          icon: [
            { required: true, message: '图标不能为空', trigger: 'blur' }
          ],
          type: [
            { required: true, message: '权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）不能为空', trigger: 'blur' }
          ],
          uri: [
            { required: true, message: '前端资源路径不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '启用状态；0->禁用；1->启用不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          sort: [
            { required: true, message: '排序不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/admin/umspermission/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.pid = data.umsPermission.pid
                this.dataForm.name = data.umsPermission.name
                this.dataForm.value = data.umsPermission.value
                this.dataForm.icon = data.umsPermission.icon
                this.dataForm.type = data.umsPermission.type
                this.dataForm.uri = data.umsPermission.uri
                this.dataForm.status = data.umsPermission.status
                this.dataForm.createTime = data.umsPermission.createTime
                this.dataForm.sort = data.umsPermission.sort
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/admin/umspermission/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'pid': this.dataForm.pid,
                'name': this.dataForm.name,
                'value': this.dataForm.value,
                'icon': this.dataForm.icon,
                'type': this.dataForm.type,
                'uri': this.dataForm.uri,
                'status': this.dataForm.status,
                'createTime': this.dataForm.createTime,
                'sort': this.dataForm.sort
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
