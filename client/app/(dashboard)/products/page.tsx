import { getTranslations } from "next-intl/server";
import { Suspense } from "react";
import { LinkButton } from "@/shared/components/link-button";
import { LoadingText } from "@/shared/components/loading-text";
import { PageHeader } from "@/shared/components/page-header";
import { ProductsList } from "@/modules/products/components/products-list";

export default async function ProductsPage({ searchParams }: PageProps<"/products">) {
  const t = await getTranslations("products");

  return (
    <>
      <PageHeader title={t("title")}>
        <LinkButton href="/products/new">{t("new")}</LinkButton>
      </PageHeader>
      <Suspense fallback={<LoadingText />}>
        <ProductsList searchParams={searchParams} />
      </Suspense>
    </>
  );
}
