<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="父级ID" prop="parentId">
      <el-input v-model="dataForm.parentId" placeholder="父级ID"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="菜单名称" prop="title">
      <el-input v-model="dataForm.title" placeholder="菜单名称"></el-input>
    </el-form-item>
    <el-form-item label="菜单级数" prop="level">
      <el-input v-model="dataForm.level" placeholder="菜单级数"></el-input>
    </el-form-item>
    <el-form-item label="菜单排序" prop="sort">
      <el-input v-model="dataForm.sort" placeholder="菜单排序"></el-input>
    </el-form-item>
    <el-form-item label="前端名称" prop="name">
      <el-input v-model="dataForm.name" placeholder="前端名称"></el-input>
    </el-form-item>
    <el-form-item label="前端图标" prop="icon">
      <el-input v-model="dataForm.icon" placeholder="前端图标"></el-input>
    </el-form-item>
    <el-form-item label="前端隐藏" prop="hidden">
      <el-input v-model="dataForm.hidden" placeholder="前端隐藏"></el-input>
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
          parentId: '',
          createTime: '',
          title: '',
          level: '',
          sort: '',
          name: '',
          icon: '',
          hidden: ''
        },
        dataRule: {
          parentId: [
            { required: true, message: '父级ID不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          title: [
            { required: true, message: '菜单名称不能为空', trigger: 'blur' }
          ],
          level: [
            { required: true, message: '菜单级数不能为空', trigger: 'blur' }
          ],
          sort: [
            { required: true, message: '菜单排序不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '前端名称不能为空', trigger: 'blur' }
          ],
          icon: [
            { required: true, message: '前端图标不能为空', trigger: 'blur' }
          ],
          hidden: [
            { required: true, message: '前端隐藏不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/admin/umsmenu/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.parentId = data.umsMenu.parentId
                this.dataForm.createTime = data.umsMenu.createTime
                this.dataForm.title = data.umsMenu.title
                this.dataForm.level = data.umsMenu.level
                this.dataForm.sort = data.umsMenu.sort
                this.dataForm.name = data.umsMenu.name
                this.dataForm.icon = data.umsMenu.icon
                this.dataForm.hidden = data.umsMenu.hidden
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
              url: this.$http.adornUrl(`/admin/umsmenu/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'parentId': this.dataForm.parentId,
                'createTime': this.dataForm.createTime,
                'title': this.dataForm.title,
                'level': this.dataForm.level,
                'sort': this.dataForm.sort,
                'name': this.dataForm.name,
                'icon': this.dataForm.icon,
                'hidden': this.dataForm.hidden
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
