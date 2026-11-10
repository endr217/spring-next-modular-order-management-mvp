import { useTranslations } from "next-intl";
import { DeleteButton } from "@/shared/components/delete-button";
import { LinkButton } from "@/shared/components/link-button";
import { TableCell, TableRow } from "@/shared/components/ui/table";
import type { Client } from "@/modules/clients/types";
import { deleteClient } from "@/modules/clients/actions/delete-client";

export function ClientRow({ client }: { client: Client }) {
  const t = useTranslations();

  return (
    <TableRow>
      <TableCell className="px-4 font-medium">{client.name}</TableCell>
      <TableCell className="px-4">{client.email}</TableCell>
      <TableCell className="px-4">{client.phone ?? t("common.empty")}</TableCell>
      <TableCell className="px-4">{client.address ?? t("common.empty")}</TableCell>
      <TableCell className="flex items-start justify-end gap-1 px-4">
        <LinkButton href={`/clients/${client.id}/edit`} variant="ghost" size="sm">
          {t("common.edit")}
        </LinkButton>
        <DeleteButton
          action={deleteClient.bind(null, client.id)}
          confirmText={t("clients.deleteConfirm", { name: client.name })}
        />
      </TableCell>
    </TableRow>
  );
}
