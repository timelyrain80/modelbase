<template>
  <a-tree checkable :tree-data="treeData" block-node="true"  v-model:expandedKeys="key">
    <template #title="{ title, key , name}">
      <div>{{ title }}</div>
      <div v-if="name">{{ name }}</div>
    </template>
  </a-tree>
</template>
<script>

export default {
  name: "Test.vue",
  data() {
    return {
      key: ['0-0','0-0-0','0-0-0-0'],
      treeData: [
        {
          title: 'parent 1',
          key: '0-0',
          name: 'test name',
          children: [
            {
              title: 'parent 1-0',
              key: '0-0-0',
              disabled: true,
              children: [
                {
                  title: 'leaf',
                  key: '0-0-0-0',
                  name: 'is leaf',
                  disableCheckbox: true,
                },
                {
                  title: 'leaf',
                  key: '0-0-0-1',
                },
              ],
            },
            {
              title: 'parent 1-1',
              key: '0-0-1',
              children: [
                {
                  key: '0-0-1-0',
                  title: 'sss',
                },
              ],
            },
          ],
        },
      ]
    }
  }
}
</script>
