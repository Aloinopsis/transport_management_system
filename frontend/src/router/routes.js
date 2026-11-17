const routes = [
  {
    path: '/',
    component: () => import('@/layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        redirect: { name: 'dashboard' },
      },
      {
        path: 'dashboard',
        name: 'dashboard',
        component: () => import('@/pages/dashboard/DashboardPage.vue'),
        meta: { title: 'Dashboard' },
      },
      {
        path: 'zlecenia',
        name: 'zlecenia',
        // Jeśli moduł nie jest jeszcze gotowy, odkomentuj poniższą linię i zakomentuj linię z właściwym komponentem
        // component: () => import('@/pages/ModulePlaceholder.vue'),
        component: () => import('@/pages/zlecenia/ZleceniaPage.vue'),
        meta: { title: 'Zlecenia' },
      },
      {
        path: 'transporty',
        name: 'transporty',
        component: () => import('@/pages/transporty/TransportyPage.vue'),
        meta: { title: 'Transporty' },
      },
      {
        path: 'kontrahenci',
        name: 'kontrahenci',
        component: () => import('@/pages/kontrahenci/KontrahenciPage.vue'),
        meta: { title: 'Kontrahenci' },
      },
      {
        path: 'kierowcy',
        name: 'kierowcy',
        component: () => import('@/pages/kierowcy/KierowcyPage.vue'),
        meta: { title: 'Kierowcy' },
      },
      {
        path: 'flota',
        name: 'flota',
        component: () => import('@/pages/flota/FlotaPage.vue'),
        meta: { title: 'Flota' },
      },
      {
        path: 'dokumenty',
        name: 'dokumenty',
        component: () => import('@/pages/dokumenty/DokumentyPage.vue'),
        meta: { title: 'Dokumenty' },
      },
      {
        path: 'faktury',
        name: 'faktury',
        component: () => import('@/pages/faktury/FakturyPage.vue'),
        meta: { title: 'Faktury' },
      },
      {
        path: 'raporty',
        name: 'raporty',
        component: () => import('@/pages/raporty/RaportyPage.vue'),
        meta: { title: 'Raporty' },
      },
      {
        path: 'ustawienia',
        name: 'ustawienia',
        component: () => import('@/pages/ustawienia/UstawieniaPage.vue'),
        meta: { title: 'Ustawienia' },
      },
      // Placeholder dla modułów w przygotowaniu
      {
        path: 'modul-placeholder',
        name: 'module-placeholder',
        component: () => import('@/pages/ModulePlaceholder.vue'),
        meta: { title: 'Moduł w przygotowaniu' },
      },
    ],
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    name: 'not-found',
    component: () => import('@/pages/ErrorNotFound.vue'),
  },
]

export default routes
