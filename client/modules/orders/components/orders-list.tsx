import { OrdersTable } from "@/modules/orders/components/orders-table";
import { StatusFilter } from "@/modules/orders/components/status-filter";
import { Pagination } from "@/shared/components/pagination";
import { getOrders } from "@/modules/orders/queries/get-orders";
import { readPage, readParam } from "@/shared/lib/search-params";

export async function OrdersList({ searchParams }: Pick<PageProps<"/orders">, "searchParams">) {
  const params = await searchParams;
  const status = readParam(params, "status");
  const orders = await getOrders({ status, page: readPage(params) });

  return (
    <>
      <StatusFilter defaultValue={status} />
      <OrdersTable orders={orders.content} />
      <Pagination page={orders.page} basePath="/orders" params={{ status }} />
    </>
  );
}
