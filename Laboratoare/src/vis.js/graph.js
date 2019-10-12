/*
    source: I started with a basic example from https://visjs.org/#gallery
*/
const nodeColor = '#98caf9';
const startColor = '#ff5530';
const acceptColor = '#7BE141';
const edgeColor = '#008fe6';
// create an array with nodes
nodes_json = [
{ id: 0, label: 's0', color: startColor },
{ id: 1, label: 's1', color: nodeColor },
{ id: 2, label: 's2', color: acceptColor },
{ id: 3, label: 's3', color: acceptColor }
];
var nodes = new vis.DataSet(nodes_json);

// create an array with edges
edges_json = [
{ from: 0, to: 1, arrows: 'to', color: { color: edgeColor }, label: 'a', font: { align: 'middle' }  },
{ from: 1, to: 2, arrows: 'to', color: { color: edgeColor }, label: 'b', font: { align: 'middle' }  },
{ from: 1, to: 3, arrows: 'to', color: { color: edgeColor }, label: 'c', font: { align: 'middle' }  },
{ from: 2, to: 3, arrows: 'to', color: { color: edgeColor }, label: 'c', font: { align: 'middle' }  },
{ from: 2, to: 2, arrows: 'to', color: { color: edgeColor }, label: 'b', font: { align: 'middle' }  },
{ from: 3, to: 3, arrows: 'to', color: { color: edgeColor }, label: 'c', font: { align: 'middle' }  },
{ from: 3, to: 2, arrows: 'to', color: { color: edgeColor }, label: 'b', font: { align: 'middle' }  }
];
var edges = new vis.DataSet(edges_json);

// create a network
var container = document.getElementById('myNetwork');
var data = {
    nodes: nodes,
    edges: edges
};
var options = {};
var network = new vis.Network(container, data, options);