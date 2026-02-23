export interface Investment {
  id: number;
  type: string;
  symbol: string;
  quantity: number;
  purchasePrice: number;
  purchaseDate: string;
  totalInvested: number;
}

export interface Summary {
  totalInvested: number;
  assetCount: number;
  totalByType: Record<string, number>;
}