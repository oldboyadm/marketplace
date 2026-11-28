import { useState } from 'react'
import { useQuery } from '@tanstack/react-query'
import { Button } from '@/components/ui/button'
import { getProducts } from '../../services/api'
import type { Category, Product } from '../../types/api'

type Props = {
  categories: Category[]
  categoryId: number | null
  subcategoryId: number | null
}

function ProductCard({ product }: { product: Product }) {
  const [imageFailed, setImageFailed] = useState(false)

  return (
    <article className="product-card">
      <div className="product-image-wrap">
        {!imageFailed && product.photoUrl ? (
          <img src={product.photoUrl} alt={product.name} onError={() => setImageFailed(true)} />
        ) : <div className="product-image-placeholder" aria-label="Изображение недоступно">▧</div>}
      </div>
      <div className="product-info">
        <span className="product-category">{product.subcategory?.name ?? 'Без подкатегории'}</span>
        <h3 title={product.name}>{product.name}</h3>
        <p>{product.description}</p>
        <strong className="product-price">{new Intl.NumberFormat('ru-RU').format(product.price)} ₸</strong>
      </div>
    </article>
  )
}

export function CustomerContent({ categories, categoryId, subcategoryId }: Props) {
  const products = useQuery({ queryKey: ['products'], queryFn: getProducts })
  const selectedCategory = categories.find((category) => category.id === categoryId)
  const subcategoryIds = new Set(selectedCategory?.subcategories.map(({ id }) => id) ?? [])
  const visibleProducts = (products.data ?? []).filter((product) => {
    if (subcategoryId !== null) return product.subcategory?.id === subcategoryId
    if (categoryId !== null) return subcategoryIds.has(product.subcategory?.id)
    return true
  })

  return (
    <section
      className="category-results product-results"
      aria-label="Товары"
      data-category-id={categoryId ?? undefined}
      data-subcategory-id={subcategoryId ?? undefined}
    >
      {products.isPending ? <p className="muted-message" role="status">Загружаем товары…</p> : products.isError ? (
        <div className="notice notice-error" role="alert"><span>{products.error.message}</span><Button variant="ghost" onClick={() => void products.refetch()}>Повторить</Button></div>
      ) : visibleProducts.length === 0 ? (
        <div className="empty-state"><span className="empty-icon" aria-hidden="true">▦</span><h2>{products.data?.length ? 'В этой категории пока нет товаров' : 'Товары скоро появятся'}</h2><p>{products.data?.length ? 'Выберите другую категорию или подкатегорию.' : 'Сейчас каталог пуст.'}</p></div>
      ) : (
        <div className="product-grid">{visibleProducts.map((product) => <ProductCard key={product.id} product={product} />)}</div>
      )}
    </section>
  )
}
