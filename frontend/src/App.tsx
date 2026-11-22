import { useEffect } from 'react'
import { Link, Navigate, Route, Routes, useLocation, useNavigate } from 'react-router'
import { useQuery } from '@tanstack/react-query'
import { CustomerHome } from './pages/CustomerHome'
import { CustomerLogin } from './pages/CustomerLogin'
import { CustomerRegister } from './pages/CustomerRegister'
import { LandingPage } from './pages/LandingPage'
import { SellerComingSoon } from './pages/SellerComingSoon'
import { getCategories } from './services/api'
import { useAuthStore } from './store/auth'
import './App.css'

function Header() {
  const user = useAuthStore((state) => state.user)
  const logout = useAuthStore((state) => state.logout)

  return (
    <header className="site-header">
      <Link className="brand" to="/" aria-label="Market — главная">
        <span className="brand-mark" aria-hidden="true">
          <svg viewBox="0 0 36 36" fill="none">
            <path d="M7.5 25V14a4.5 4.5 0 0 1 9 0v11m0 0V14a4.5 4.5 0 0 1 9 0v11" />
          </svg>
        </span>
        <span className="brand-copy">
          <span className="brand-wordmark">market</span>
          <span className="brand-tagline">Не Kaspi. Зато свой.</span>
        </span>
      </Link>
      <div className="header-actions">
        {user ? <><span className="user-name">{user.name}</span><button className="button button-quiet" onClick={logout}>Выйти</button></> : <><Link className="button button-quiet" to="/customer/login">Войти</Link><Link className="button button-primary header-register" to="/customer/register">Регистрация</Link></>}
      </div>
    </header>
  )
}

function AppContent() {
  const initialize = useAuthStore((state) => state.initialize)
  const navigate = useNavigate()
  const currentPath = useLocation().pathname
  const isCustomerArea = currentPath.startsWith('/customer')
  const categoriesQuery = useQuery({ queryKey: ['categories'], queryFn: getCategories, enabled: isCustomerArea })

  useEffect(() => {
    void initialize()
  }, [initialize])

  return (
    <div className="app-shell">
      <Header />
      <main>
        <Routes>
          <Route path="/" element={<LandingPage />} />
          <Route path="/seller" element={<SellerComingSoon />} />
          <Route path="/customer" element={<CustomerHome categories={categoriesQuery} />} />
          <Route path="/customer/categories" element={<Navigate to="/customer" replace />} />
          <Route path="/customer/login" element={<CustomerLogin onSuccess={() => navigate('/customer')} />} />
          <Route path="/customer/register" element={<CustomerRegister onSuccess={() => navigate('/customer')} />} />
          <Route path="*" element={<section className="empty-state"><h1>Страница не найдена</h1><Link to="/customer">На главную</Link></section>} />
        </Routes>
      </main>
      <footer className="site-footer"><span>market</span><span>Покупки начинаются здесь</span></footer>
    </div>
  )
}

export default AppContent
