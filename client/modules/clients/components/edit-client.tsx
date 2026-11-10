import { updateClient } from "@/modules/clients/actions/update-client";
import { ClientForm } from "@/modules/clients/components/client-form";
import { getClient } from "@/modules/clients/queries/get-client";

export async function EditClient({ params }: Pick<PageProps<"/clients/[id]/edit">, "params">) {
  const { id } = await params;
  const client = await getClient(id);

  return <ClientForm action={updateClient.bind(null, client.id)} client={client} />;
}
