import './assets/main.css'

import {createApp, getCurrentInstance} from 'vue'
import {createPinia} from 'pinia'
import Antd from 'ant-design-vue'
import App from './App.vue'
import router from './router'
import './utils/prototypes'



const app = createApp(App)

app.use(Antd)
app.use(createPinia())
app.use(router)
app.mount('#app')
