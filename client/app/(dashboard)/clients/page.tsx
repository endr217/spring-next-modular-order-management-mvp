import { getTranslations } from "next-intl/server";
import { Suspense } from "react";
import { ClientsList } from "@/modules/clients/components/clients-list";
import { LinkButton } from "@/shared/components/link-button";
import { LoadingText } from "@/shared/components/loading-text";
import { PageHeader } from "@/shared/components/page-header";

export default async function ClientsPage({ searchParams }: PageProps<"/clients">) {
  const t = await getTranslations("clients");

  return (
    <>
      <PageHeader title={t("title")}>
        <LinkButton href="/clients/new">{t("new")}</LinkButton>
      </PageHeader>
      <Suspense fallback={<LoadingText />}>
        <ClientsList searchParams={searchParams} />
      </Suspense>
    </>
  );
}
