import {createRouter, createWebHistory} from 'vue-router'
import session from '../utils/sessionStorage.js'
const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            redirect: '/index'
        },
        {
            path: '/index',
            name: 'index',
            // route level code-splitting
            // this generates a separate chunk (About.[hash].js) for this route
            // which is lazy-loaded when the route is visited.
            component: () => import('../views/design/ProjectList.vue')
        },
        {
            path: '/login',
            name: 'login',
            meta: {public: true},
            component: () => import('../views/sys/Login.vue')
        },
        {
            path: '/project/:id',
            name: 'project-detail',
            component: () => import('../views/design/ModelIndex.vue')
        },
        {
            path: '/test',
            component: () => import('../views/design/Test.vue')
        }
    ]
})

router.beforeEach((to, from, next) => {
    // 使用了cookie 不再设置导航守卫
    next()
})

export default router
