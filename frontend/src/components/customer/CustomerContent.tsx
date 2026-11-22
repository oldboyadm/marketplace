type Props = {
  categoryId: number | null
  subcategoryId: number | null
}

export function CustomerContent({ categoryId, subcategoryId }: Props) {
  return (
    <section
      className="category-results product-results"
      aria-label="Товары"
      data-category-id={categoryId ?? undefined}
      data-subcategory-id={subcategoryId ?? undefined}
    >
      <div className="products-placeholder">
        <span className="placeholder-icon" aria-hidden="true">▦</span>
        <h2>Товары появятся здесь</h2>
      </div>
    </section>
  )
}
