import { getTranslations } from "next-intl/server";
import { FilterSelect } from "@/shared/components/filter-select";
import { Pagination } from "@/shared/components/pagination";
import { ProductsTable } from "@/modules/products/components/products-table";
import { SearchForm } from "@/shared/components/search-form";
import { getCategories } from "@/modules/products/queries/get-categories";
import { getProducts } from "@/modules/products/queries/get-products";
import { readPage, readParam } from "@/shared/lib/search-params";

export async function ProductsList({ searchParams }: Pick<PageProps<"/products">, "searchParams">) {
  const params = await searchParams;
  const search = readParam(params, "search");
  const categoryId = readParam(params, "categoryId");
  const [t, products, categories] = await Promise.all([
    getTranslations("products"),
    getProducts({ search, categoryId, page: readPage(params) }),
    getCategories(),
  ]);

  return (
    <>
      <SearchForm action="/products" placeholder={t("searchPlaceholder")} defaultValue={search}>
        <FilterSelect
          name="categoryId"
          label={t("fields.category")}
          allLabel={t("allCategories")}
          defaultValue={categoryId}
          options={categories.map((category) => ({ value: category.id, label: category.name }))}
        />
      </SearchForm>
      <ProductsTable products={products.content} />
      <Pagination page={products.page} basePath="/products" params={{ search, categoryId }} />
    </>
  );
}
