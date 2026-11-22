import { Link } from 'react-router'

export function SellerComingSoon() {
  return (
    <section className="seller-soon">
      <span className="seller-soon-icon" aria-hidden="true">✦</span>
      <span className="eyebrow">РАЗДЕЛ ПРОДАВЦА</span>
      <h1>Мы уже готовим это</h1>
      <p>Кабинет продавца пока в разработке. Скоро здесь появится всё необходимое для работы с товарами и заказами.</p>
      <div className="seller-soon-actions">
        <Link className="button button-primary" to="/customer">Перейти к покупкам <span aria-hidden="true">→</span></Link>
        <Link className="button button-quiet" to="/">На главную</Link>
      </div>
    </section>
  )
}
