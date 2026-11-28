import type { ReactNode } from 'react'
import { Link } from 'react-router'

export function AuthLayout({ title, subtitle, children, footer }: { title: string; subtitle: string; children: ReactNode; footer: ReactNode }) {
  return (
    <div className="auth-page">
      <div className="auth-decoration" aria-hidden="true"><span>✦</span><span>✳</span><span>◈</span></div>
      <section className="auth-card">
        <Link className="auth-brand" to="/customer">
          <span className="brand-mark" aria-hidden="true">
            <svg viewBox="0 0 36 36" fill="none">
              <path d="M7.5 25V14a4.5 4.5 0 0 1 9 0v11m0 0V14a4.5 4.5 0 0 1 9 0v11" />
            </svg>
          </span>
          <span className="brand-wordmark">market</span>
        </Link>
        <span className="eyebrow">РАДЫ ВАС ВИДЕТЬ</span>
        <h1>{title}</h1>
        <p className="auth-subtitle">{subtitle}</p>
        {children}
        <div className="auth-footer">{footer}</div>
      </section>
      <p className="auth-caption">Находите больше. Выбирайте с удовольствием.</p>
    </div>
  )
}

export function AuthError({ error }: { error: string }) {
  return <div className="form-error" role="alert">{error}</div>
}

