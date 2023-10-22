<template>
  <div>
    <a-page-header title="项目清单" back-icon="false"/>
    <div class="app-container">
      <div class="action-bar">
        <a-button type="primary" @click="doModify({})">新建项目</a-button>
      </div>
      <a-table :columns="tableHeaders" :data-source="projectList">
        <template #bodyCell="{column, record}">
          <template v-if="column.title === '项目名称'">
            <a @click="doSelect(record.id)">{{ record.projectName }}</a>
          </template>
          <template v-else-if="column.title = '操作'">
            <a-space>
              <a @click="doModify(record)">修改</a>
              <a-popconfirm title="确认删除" ok-text="是" cancel-text="否" @confirm="doDelete(record.id)">
                <a>删除</a>
              </a-popconfirm>
              <a>设置</a>
            </a-space>
          </template>
        </template>
      </a-table>
    </div>

    <a-modal v-model:open="ctl.showDialog" :title="this.formData.id ? '修改项目信息' : '新建项目信息'">
      <a-form ref="form1" :model="formData">
        <a-tabs>
          <a-tab-pane key="base" tab="项目信息">
            <a-form-item label="项目名称" name="projectName" :rules="{required:true, message:'请输入项目名称'}">
              <a-input v-model:value="formData.projectName"/>
            </a-form-item>
          </a-tab-pane>
          <!--          <a-tab-pane key="datatype" tab="数据类型">-->
          <!--            <a-form-item required="true" label="项目名称1">-->
          <!--              <a-input v-model:value="formData.projectName1"/>-->
          <!--            </a-form-item>-->
          <!--          </a-tab-pane>-->
        </a-tabs>
      </a-form>
      <template #footer>
        <a-space>
          <a-button @click="doClose">关闭</a-button>
          <a-button @click="doSave" type="primary">保存</a-button>
        </a-space>
      </template>
    </a-modal>
  </div>
</template>

<script>
import project from '../../request/Project.js'
import {message} from "ant-design-vue";

export default {
  name: "ProjectList",
  data() {
    return {
      tableHeaders: [
        {
          title: '项目名称',
          dataIndex: 'name'
        },
        {
          title: '操作',
          width: 200
        },
      ],
      projectList: [{}],
      formData: {
        projectName: '',
        id: ''
      },
      ctl: {
        showDialog: false
      }
    }
  },
  created() {
    this.query()
  },
  methods: {
    doSelect(id) {
      this.$router.push({path: `/project/${id}`})
    },
    query() {
      project.query({}).then(resp => this.projectList = resp.data, err => message.error(err.title))
    },
    doDelete(id) {
      project.delete(id).then(resp => this.query(), err => message.error(err.title))
    },
    doClose() {
      this.ctl.showDialog = false
    },
    doSave() {
      this.$refs.form1.validate().then(f => {
        project.save(this.formData).then(resp => {
          this.doClose()
          this.query()
        }, err => {
          message.error(err.title)
        })
      })
    },
    doModify(p) {
      const temp = {}
      Object.assign(temp, p)
      this.formData = temp
      this.ctl.showDialog = true
    }
  }
}
</script>

<style scoped>

</style>