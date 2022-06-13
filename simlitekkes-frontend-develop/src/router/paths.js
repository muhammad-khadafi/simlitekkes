/**
 * Define all of your application routes here
 * for more information on routes,
 * official documentation https://router.vuejs.org/en/
 */

 import Dashboard from '@/views/Dashboard.vue'
 import UserProfile from '@/views/UserProfile.vue'
 import CrudDataTable from '@/views/CrudDataTable.vue'
 import DashboardView from '@/views/DashboardView.vue'
 import LoginHome from '@/views/LoginHome.vue'
 import LoginForm from '@/views/LoginForm.vue'
 import ForgotPasswordForm from '@/views/ForgotPasswordForm.vue'
 import File from '@/views/File.vue'
 import User from '@/views/User.vue'
 import Sandbox from '@/views/Sandbox.vue'
 import Userweb from '@/views/UserWeb.vue'
 import Menu from '@/views/Menu.vue'
 import Role from '@/views/Role.vue'
 import SkemaPenelitianList from '@/views/skemapenelitian/SkemaPenelitianList.vue'
 import SkemaPenelitianView from '@/views/skemapenelitian/SkemaPenelitianView.vue'
 import ProfilPoltekkes from '@/views/ProfilPoltekkes.vue'
 import TemaList from '@/views/TemaList.vue'
 import Topik from '@/views/Topik.vue'
 import DosenList from '@/views/dosen/DosenList.vue'
 import DosenView from '@/views/dosen/DosenView.vue'
 import ChangePassword from '@/views/usersetting/ChangePassword.vue'
 import UserRole from '@/views/UserRole'
 import RoleMenu from '@/views/RoleMenu'
 import OrganisasiList from '@/views/organisasi/OrganisasiList.vue'
 import OrganisasiAddUpdate from '@/views/organisasi/OrganisasiAddUpdate.vue'
 import DashboardDosen from '@/views/dosen/dashboard/Dashboard.vue'
 import TopikUnggulan from '@/views/TopikUnggulan.vue'
 import DokumenOrganisasi from '@/views/dokumenorganisasi/DokumenOrganisasi.vue'
 import ProfilDosen from '@/views/dosen/dashboard/Profil.vue'
 import Jurnal from '@/views/dosen/dashboard/Jurnal.vue'
 import JurnalForm from '@/views/dosen/dashboard/JurnalForm.vue'
 import DokumenOrganisasiForm from '@/views/dokumenorganisasi/DokumenOrganisasiForm.vue'
 import Home from '@/views/Home.vue'
 import Hki from '@/views/dosen/dashboard/Hki.vue'
 import HkiForm from '@/views/dosen/dashboard/HkiForm.vue'
 import Prosiding from '@/views/dosen/dashboard/Prosiding.vue'
 import ProsidingForm from '@/views/dosen/dashboard/ProsidingForm.vue'
 import RekapList from '@/views/penelitian/RekapListView.vue'
 import UsulanBySkemaTahun from '@/views/penelitian/UsulanBySkemaTahun.vue'
 import Page404 from '@/views/404.vue'
 import Buku from '@/views/dosen/dashboard/Buku.vue'
 import BukuForm from '@/views/dosen/dashboard/BukuForm.vue'
 import Page403 from '@/views/403.vue'
 import PlottingReviewerList from '@/views/plottingreviewer/PlottingReviewerList.vue'
 import RiwayatPenelitianList from '@/views/dosen/dashboard/Penelitian.vue'
 import PenelitianOverview from '@/views/penelitian/overview/Overview.vue'
 import IdentitasUsulan from '@/views/penelitian/overview/Identitas.vue'
 import UsulanPenelitian from '@/views/penelitian/UsulanPenelitian.vue'
 import RencanaKegiatan from '@/views/penelitian/overview/RencanaKegiatan'
 import RabUsulan from '@/views/penelitian/overview/RAB.vue'
 import JenisTKT from '@/views/JenisTKT.vue'
 import MitraPenelitian from '@/views/penelitian/overview/MitraPenelitian'
 import SubstansiLuaranUsulan from '@/views/penelitian/overview/SubstansiLuaran.vue'
 import KirimUsulan from '@/views/penelitian/overview/KirimUsulan.vue'
 import PenugasanReviewerList from '@/views/penugasanreviewer/PenugasanReviewerList.vue'
 import RiwayatPerubahanStatus from '@/views/penelitian/overview/Riwayat.vue'
 import ViewUsulan from '@/views/penelitian/overview/ViewUsulan.vue'
 import RiwayatReview from '@/views/RiwayatReview.vue'
 import DashboardNew from '@/views/dashboard/DashboardNew.vue'
 import ReviewList from '@/views/ReviewList.vue'
 import LaporanAnggaran from '@/views/laporan/LaporanAnggaran.vue'
 import ProfilPengguna from '@/views/ProfilPengguna.vue'
 import BeritaList from '@/views/berita/BeritaList.vue'
 import DaftarRiwayatUsulan from '@/views/DaftarRiwayatUsulan.vue'
 import DaftarUsulanList from '@/views/DaftarUsulanList.vue'
 import RiwayatUsulanList from '@/views/RiwayatUsulanList.vue'
 import BeritaAddUpdate from '@/views/berita/BeritaAddUpdate.vue'
 import BeritaDetailForAdmin from '@/views/berita/BeritaDetailForAdmin.vue'
 import BeritaDetailForNonAdmin from '@/views/berita/BeritaDetailForNonAdmin.vue'
 import LogAktifitas from '@/views/LogAktifitas.vue'

export default [
  {
    path: '/',
    component: LoginHome,
    children: [
      {
        path: '',
        name: 'LOGIN_HOME',
        component: LoginForm,
        meta: { loginForm: true },
      },
      {
        path: '/forgot-password',
        name: 'FORGOT_PASSWORD',
        component: ForgotPasswordForm,
        meta: { ForgotPasswordForm: true },
      },
      {
        path: '/404',
        name: '404',
        component: Page404,
        meta: { Page404: true },
      },
      {
        path: '/403',
        name: '403',
        component: Page403,
        meta: { Page403: true },
      },
    ],
  },
  {
    path: '/dashboard-view',
    component: DashboardView,
    children: [
      {
        path: '/home',
        name: 'HOME',
        component: Home,
        meta: { requiresAuth: true },
      },
      {
        path: '/dashboard-old',
        name: 'DASHBOARD',
        component: Dashboard,
        meta: { requiresAuth: true },
      },
      {
        path: '/user-profile',
        name: 'USER_PROFILE',
        component: UserProfile,
        meta: { requiresAuth: true },
      },
      {
        path: '/crud',
        name: 'SAMPLE_CRUD',
        component: CrudDataTable,
        meta: { requiresAuth: true },
      },
      {
        path: '/file',
        name: 'FILE',
        component: File,
        meta: { requiresAuth: true },
      },
      {
        path: '/user',
        name: 'USER',
        component: User,
        meta: { requiresAuth: true },
      },
      {
        path: '/sandbox',
        name: 'SANDBOX',
        component: Sandbox,
        meta: { requiresAuth: true },
      },
      {
        path: '/userweb',
        name: 'USERWEB',
        component: Userweb,
        meta: { requiresAuth: true, kode: 'USER' },
      },
      {
        path: '/menu',
        name: 'MENU',
        component: Menu,
        meta: { requiresAuth: true },
      },
      {
        path: '/role',
        name: 'ROLE',
        component: Role,
        meta: { requiresAuth: true },
      },
      {
        path: '/skema-penelitian',
        name: 'SKEMA_PENELITIAN',
        component: SkemaPenelitianList,
        meta: { requiresAuth: true },
      },
      {
        path: '/skema-penelitian/:id',
        name: 'DETAIL_SKEMA_PENELITIAN',
        component: SkemaPenelitianView,
        meta: { requiresAuth: true },
      },
      {
        path: '/profil-poltekkes',
        name: 'PROFIL_POLTEKKES',
        component: ProfilPoltekkes,
        meta: { requiresAuth: true },
      },
      {
        path: '/tema',
        name: 'TEMA',
        component: TemaList,
        meta: { requiresAuth: true },
      },
      {
        path: '/topik/:id',
        name: 'TOPIK',
        component: Topik,
        meta: { requiresAuth: true },
      },
      {
        path: '/user-role',
        name: 'USER_ROLE',
        component: UserRole,
        meta: { requiresAuth: true },
      },
      {
        path: '/role-menu',
        name: 'ROLE_MENU',
        component: RoleMenu,
        meta: { requiresAuth: true },
      },
      {
        path: '/dosen',
        name: 'DOSEN',
        component: DosenList,
        meta: { requiresAuth: true },
      },
      {
        path: '/dosen/:id',
        name: 'DATA_DOSEN',
        component: DosenView,
        meta: { requiresAuth: true },
      },
      {
        path: '/change-password',
        name: 'CHANGE_PASSWORD',
        component: ChangePassword,
        meta: { requiresAuth: true },
      },
      {
        path: '/organisasi',
        name: 'ORGANISASI',
        component: OrganisasiList,
        meta: { requiresAuth: true },
      },
      {
        path: '/organisasi/:id',
        name: 'ORGANISASI_ADD_UPDATE',
        component: OrganisasiAddUpdate,
        meta: { requiresAuth: true },
      },
      {
        path: '/profil-dosen',
        component: DashboardDosen,
        children: [
          {
            path: '',
            name: 'PROFIL_DOSEN',
            component: ProfilDosen,
            meta: { requiresAuth: true },
          },
          {
            path: '/jurnal-dosen',
            name: 'JURNAL',
            component: Jurnal,
            meta: { requiresAuth: true },
          },
          {
            path: '/jurnal-dosen/:id',
            name: 'TAMBAH_UBAH_JURNAL',
            component: JurnalForm,
            meta: { requiresAuth: true },
          },
          {
            path: '/hki-dosen',
            name: 'HKI',
            component: Hki,
            meta: { requiresAuth: true },
          },
          {
            path: '/hki-dosen/:id',
            name: 'TAMBAH_UBAH_HKI',
            component: HkiForm,
            meta: { requiresAuth: true },
          },
          {
            path: '/prosiding-dosen',
            name: 'PROSIDING',
            component: Prosiding,
            meta: { requiresAuth: true },
          },
          {
            path: '/prosiding-dosen/:id',
            name: 'TAMBAH_UBAH_PROSIDING',
            component: ProsidingForm,
            meta: { requiresAuth: true },
          },
          {
            path: '/buku-dosen',
            name: 'BUKU',
            component: Buku,
            meta: { requiresAuth: true },
          },
          {
            path: '/buku-dosen/:id',
            name: 'TAMBAH_UBAH_BUKU',
            component: BukuForm,
            meta: { requiresAuth: true },
          },
          {
            path: '/penelitian-dosen',
            name: 'RIWAYAT_PENELITIAN',
            component: RiwayatPenelitianList,
            meta: { requiresAuth: true },
          },
        ],
        meta: { requiresAuth: true },
      },
      {
        path: '/topik-unggulan',
        name: 'TOPIK_UNGGULAN',
        component: TopikUnggulan,
        meta: { requiresAuth: true },
      },
      {
        path: '/dokumen-organisasi',
        name: 'DOKUMEN_ORGANISASI',
        component: DokumenOrganisasi,
        meta: { requiresAuth: true },
      },
      {
        path: '/dokumen-organisasi-add',
        name: 'DOKUMEN_ORGANISASI_ADD',
        component: DokumenOrganisasiForm,
        meta: { requiresAuth: true },
      },
      {
        path: '/dokumen-organisasi-update',
        name: 'DOKUMEN_ORGANISASI_UPDATE',
        component: DokumenOrganisasiForm,
        meta: { requiresAuth: true },
      },
      {
        path: '/usulan',
        name: 'DAFTAR_RIWAYAT_USULAN',
        component: DaftarRiwayatUsulan,
        children: [
            {
              path: '/usulan',
              name: 'DAFTAR_USULAN',
              component: DaftarUsulanList,
              meta: { requiresAuth: true },
            },
            {
              path: '/riwayat-usulan',
              name: 'RIWAYAT_USULAN',
              component: RiwayatUsulanList,
              meta: { requiresAuth: true },
            },
        ],
        meta: { requiresAuth: true },
      },
      {
        path: '/penelitian/rekap',
        name: 'REKAP_USULAN',
        component: RekapList,
        meta: { requiresAuth: true },
      },
      {
        path: '/penelitian/usulan/:idSkemaPenelitian/:tahunUsulan',
        name: 'USULAN_PER_SKEMA_TAHUN',
        component: UsulanBySkemaTahun,
        meta: { requiresAuth: true },
      },
      {
        path: '/penelitian/:id/identitas-usulan',
        name: 'DETAIL_USULAN',
        component: PenelitianOverview,
        children: [
          {
            path: '/penelitian/:id/identitas-usulan',
            name: 'IDENTITAS_USULAN',
            component: IdentitasUsulan,
            meta: { requiresAuth: true },
          },
          {
            path: '/penelitian/:id/substansi-usulan',
            name: 'SUBSTANSI_LUARAN_USULAN',
            component: SubstansiLuaranUsulan,
            meta: { requiresAuth: true },
          },
          {
            path: '/penelitian/:id/rencana-kegiatan',
            name: 'RENCANA_KEGIATAN',
            component: RencanaKegiatan,
            meta: { requiresAuth: true },
          },
          {
            path: '/penelitian/:id/rab',
            name: 'RAB_USULAN',
            component: RabUsulan,
            meta: { requiresAuth: true },
          },
          {
            path: '/penelitian/:id/mitra-penelitian',
            name: 'MITRA_PENELITIAN',
            component: MitraPenelitian,
            meta: { requiresAuth: true },
          },
          {
            path: '/penelitian/:id/kirim-usulan',
            name: 'KIRIM_USULAN',
            component: KirimUsulan,
            meta: { requiresAuth: true },
          },
          {
            path: '/penelitian/:id/riwayat-perubahan-status',
            name: 'RIWAYAT_PERUBAHAN_STATUS',
            component: RiwayatPerubahanStatus,
            meta: { requiresAuth: true },
          },
        ],
        meta: { requiresAuth: true },
      },
      {
        path: '/plotting-reviewer',
        name: 'PLOTTING_REVIEWER',
        component: PlottingReviewerList,
        meta: { requiresAuth: true },
      },
      {
        path: '/usulan-baru/penelitian',
        name: 'USULAN_PENELITIAN',
        component: UsulanPenelitian,
        meta: { requiresAuth: true },
      },
      {
        path: '/jenis-tkt',
        name: 'JENIS_TKT',
        component: JenisTKT,
        meta: { requiresAuth: true },
      },
      {
        path: '/penugasan-reviewer',
        name: 'PENUGASAN_REVIEWER',
        component: PenugasanReviewerList,
        meta: { requiresAuth: true },
      },
      {
        path: '/penelitian/:id/view-usulan',
        name: 'VIEW_USULAN',
        component: ViewUsulan,
        meta: { requiresAuth: true },
      },
      {
        path: '/riwayat-review',
        name: 'RIWAYAT_REVIEW',
        component: RiwayatReview,
        meta: { requiresAuth: true },
      },
      {
        path: '/dashboard',
        name: 'DASHBOARD_NEW',
        component: DashboardNew,
        meta: { requiresAuth: true },
      },
      {
        path: '/review',
        name: 'DAFTAR_REVIEW',
        component: ReviewList,
        meta: { requiresAuth: true },
      },
      {
        path: '/laporan-anggaran',
        name: 'LAPORAN_ANGGARAN',
        component: LaporanAnggaran,
        meta: { requiresAuth: true },
      },
      {
        path: '/profil-pengguna',
        name: 'PROFIL_PENGGUNA',
        component: ProfilPengguna,
        meta: { requiresAuth: true },
      },
      {
        path: '/berita',
        name: 'BERITA',
        component: BeritaList,
        meta: { requiresAuth: true },
      },
      {
        path: '/berita/:id',
        name: 'BERITA_ADD_UPDATE',
        component: BeritaAddUpdate,
        meta: { requiresAuth: true },
      },
      {
        path: '/berita/detail/:id',
        name: 'BERITA_DETAIL_FOR_ADMIN',
        component: BeritaDetailForAdmin,
        meta: { requiresAuth: true },
      },
      {
        path: '/berita/detail-nonadmin/:id',
        name: 'BERITA_DETAIL_FOR_NON_ADMIN',
        component: BeritaDetailForNonAdmin,
        meta: { requiresAuth: true },
      },
      {
        path: '/log-aktifitas',
        name: 'LOG_AKTIFITAS',
        component: LogAktifitas,
        meta: { requiresAuth: true },
      },
    ],
  },
]
