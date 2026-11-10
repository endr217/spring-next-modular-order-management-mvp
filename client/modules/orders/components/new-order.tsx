import { OrderForm } from "@/modules/orders/components/order-form";
import { getClients } from "@/modules/clients/queries/get-clients";
import { getProducts } from "@/modules/products/queries/get-products";

export async function NewOrder() {
  const [clients, products] = await Promise.all([getClients({ size: 1000 }), getProducts({ size: 1000 })]);

  return <OrderForm clients={clients.content} products={products.content} />;
}
