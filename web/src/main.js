import './assets/main.css'

import {createApp} from 'vue'
import {createPinia} from 'pinia'
import Antd, {message} from 'ant-design-vue'
import App from './App.vue'
import router from './router'
import './utils/prototypes'

const app = createApp(App)

app.use(Antd)
app.use(createPinia())
app.use(router)
app.use(message)

app.mount('#app')

