import { useTranslations } from "next-intl";
import { DataTable } from "@/shared/components/data-table";
import { EmptyRow } from "@/shared/components/empty-row";
import type { Client } from "@/modules/clients/types";
import { ClientRow } from "@/modules/clients/components/client-row";

export function ClientsTable({ clients }: { clients: Client[] }) {
  const t = useTranslations("clients");
  const head = [t("fields.name"), t("fields.email"), t("fields.phone"), t("fields.address"), ""];

  return (
    <DataTable head={head}>
      {clients.length === 0 && <EmptyRow colSpan={head.length} text={t("empty")} />}
      {clients.map((client) => (
        <ClientRow key={client.id} client={client} />
      ))}
    </DataTable>
  );
}
