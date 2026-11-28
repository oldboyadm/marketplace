import { ShoppingBag, Store } from 'lucide-react'
import { Link } from 'react-router'

export function LandingPage() {
  return (
    <div className="landing-page">
      <section className="landing-hero">
        <div className="landing-orbit orbit-one" aria-hidden="true" />
        <div className="landing-orbit orbit-two" aria-hidden="true" />
        <div className="landing-copy">
          <span className="eyebrow">ДЛЯ ПОКУПАТЕЛЕЙ И ПРОДАВЦОВ</span>
          <h1>Пока не Kaspi.</h1>
          <p>Уже место, где можно купить и продать.</p>
        </div>
        <div className="marketplace-visual" aria-hidden="true">
          <div className="visual-glow" />
          <div className="audience-card buyer-card">
            <span className="audience-symbol buyer-symbol"><ShoppingBag size={20} strokeWidth={1.8} /></span>
            <span className="audience-copy"><small>ПОКУПАТЕЛЬ</small><strong>Нашёл нужное</strong></span>
          </div>
          <div className="exchange-mark"><span>↔</span></div>
          <div className="audience-card seller-card">
            <span className="audience-symbol seller-symbol"><Store size={20} strokeWidth={1.8} /></span>
            <span className="audience-copy"><small>ПРОДАВЕЦ</small><strong>Нашёл клиента</strong></span>
          </div>
          <span className="visual-sparkle visual-sparkle-one">✦</span>
          <span className="visual-sparkle visual-sparkle-two">✳</span>
        </div>
      </section>

      <section className="entry-section">
        <div className="entry-heading"><span className="eyebrow">С ЧЕГО НАЧНЁМ?</span><h2>Выберите свой раздел</h2></div>
        <div className="entry-cards">
          <Link className="entry-card customer-entry" to="/customer">
            <span className="entry-icon" aria-hidden="true">⌕</span>
            <span className="entry-card-copy"><strong>Я покупатель</strong><small>Перейти в каталог товаров и категорий</small></span>
            <span className="entry-arrow" aria-hidden="true">↗</span>
          </Link>
          <Link className="entry-card seller-entry" to="/seller">
            <span className="entry-icon" aria-hidden="true">▧</span>
            <span className="entry-card-copy"><strong>Я продавец</strong><small>Информация для тех, кто хочет продавать</small></span>
            <span className="entry-arrow" aria-hidden="true">↗</span>
          </Link>
        </div>
      </section>
    </div>
  )
}
