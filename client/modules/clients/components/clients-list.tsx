import { getTranslations } from "next-intl/server";
import { ClientsTable } from "@/modules/clients/components/clients-table";
import { Pagination } from "@/shared/components/pagination";
import { SearchForm } from "@/shared/components/search-form";
import { getClients } from "@/modules/clients/queries/get-clients";
import { readPage, readParam } from "@/shared/lib/search-params";

export async function ClientsList({ searchParams }: Pick<PageProps<"/clients">, "searchParams">) {
  const params = await searchParams;
  const search = readParam(params, "search");
  const [t, clients] = await Promise.all([
    getTranslations("clients"),
    getClients({ search, page: readPage(params) }),
  ]);

  return (
    <>
      <SearchForm action="/clients" placeholder={t("searchPlaceholder")} defaultValue={search} />
      <ClientsTable clients={clients.content} />
      <Pagination page={clients.page} basePath="/clients" params={{ search }} />
    </>
  );
}
