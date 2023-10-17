<template>
  <div>
    <a-page-header title="项目清单" back-icon="false"/>
    <div class="app-container">
      <a-table :columns="tableHeaders" :data-source="tableBody">
        <template #bodyCell="{column, record}">
          <template v-if="column.title === '项目名称'">
            <a @click="doSelect(record.id)">{{ record.name }}</a>
          </template>
          <template v-else-if="column.title = '操作'">
            <a-space>
              <a>修改</a>
              <a-popconfirm title="确认删除" ok-text="是" cancel-text="否" @confirm="">
                <a>删除</a>
              </a-popconfirm>
              <a>设置</a>
            </a-space>
          </template>
        </template>
      </a-table>
    </div>
  </div>
</template>

<script>
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
      tableBody: [{
        id: '1',
        name: 'mock name'
      }]
    }
  },
  methods: {
    doSelect(id) {
      this.$router.push({path: `/project/${id}`})
    }
  }
}
</script>

<style scoped>

</style>